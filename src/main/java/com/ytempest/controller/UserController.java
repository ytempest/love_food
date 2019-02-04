package com.ytempest.controller;

import com.ytempest.encrypt.DecryptUtils;
import com.ytempest.encrypt.MD5Utils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.CookInfoService;
import com.ytempest.service.TopicInfoService;
import com.ytempest.service.UserInfoService;
import com.ytempest.util.LogUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.TopicInfoVO;
import com.ytempest.vo.UserInfoVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final String TAG = "UserController";

    @Resource(name = "UserInfoService")
    private UserInfoService userService;

    @Resource(name = "CookInfoService")
    private CookInfoService cookService;

    @Resource(name = "TopicInfoService")
    private TopicInfoService topicService;

    /**
     * 只能使用账号登录
     */
    @PostMapping("/login")
    public BaseResult login(@RequestParam("account") String account,
                            @RequestParam("password") String password) {
        BaseResult result = ResultUtils.result();

        try {
            UserInfoVO userInfoVO = userService.login(account, password);
            ResultUtils.setSuccess(result, "登录成功", userInfoVO);
        } catch (ServiceException e) {
            String msg = e.getMessage();
            ResultUtils.setError(result, msg, ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 根据账号判断该账号是否已经存在
     */
    @GetMapping("/judge")
    public BaseResult judge(@RequestParam("account") String account) {
        BaseResult result = ResultUtils.result();
        try {
            UserInfoVO userInfo = userService.selectByAccount(account);
            if (userInfo == null) {
                ResultUtils.setError(result, "该账号可以使用", false);
            } else {
                ResultUtils.setSuccess(result, "该账号已经存在", true);
            }
        } catch (Exception e) {
            ResultUtils.setSuccess(result, "该账号已经存在", true);
        }
        return result;
    }

    /**
     * 注册用户
     * 如果手机号码或者账号已经注册，则不允许注册
     */
    @PostMapping("/register")
    public BaseResult register(@RequestParam("account") String account,
                               @RequestParam("pwd") String pwd,
                               @RequestParam("phone") String phone) {

        BaseResult result = ResultUtils.result();
        try {

            // 1、判断手机号码是否已经注册
            boolean isUse = userService.isPhoneHadRegister(phone);
            if (isUse) {
                ResultUtils.setSuccess(result, "该手机号码已经被注册", ResultUtils.NullObj);
                return result;
            }

            // 2、判断该账号是否已经被使用
            UserInfoVO judgeUserInfo = userService.selectByAccount(account);
            if (judgeUserInfo != null) {
                ResultUtils.setSuccess(result, "该账号已被使用", ResultUtils.NullObj);
                return result;
            }

            String realPwd = DecryptUtils.decrypt(pwd);
            String savePwd = MD5Utils.encode(realPwd);

            UserInfoVO userInfo = UserInfoVO.baseUserInfo();
            userInfo.setUserAccount(account);
            userInfo.setUserPwd(savePwd);
            userInfo.setUserPhone(phone);
            userInfo.setUserRegisterTime(new Date());
            userService.addUser(userInfo);
            LogUtils.d(TAG, "register: user=" + userInfo);
            ResultUtils.setSuccess(result, "注册成功", userInfo);
        } catch (IllegalStateException e) {
            ResultUtils.setError(result, "非法数据", ResultUtils.NullObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResultUtils.setError(result, "注册失败，请重试", ResultUtils.NullObj);
        }
        return result;
    }


    /**
     * 获取用户所有的菜谱
     */
    @GetMapping("/cookList")
    public BaseResult getUserCookList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("userId") Long userId) {

        BaseResult result = ResultUtils.result();
        try {
            PageVO<CookBaseInfoVO> list = cookService.getUserCookList(userId, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", list);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 根据用户Id获取该用户发布的话题列表
     */
    @GetMapping("/topicList")
    public BaseResult getUserTopicList(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("userId") Long userId) {

        BaseResult result = ResultUtils.result();
        try {
            PageVO<TopicInfoVO> topicList = topicService.getUserTopicList(userId, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", topicList);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
        }
        return result;
    }

    /**
     * 根据用户Id修改用户信息
     */
    @RequestMapping(value = "/updateInfo")
    public BaseResult updateUserInfo(HttpServletRequest request) {

        BaseResult result = ResultUtils.result();
        try {
            UserInfoVO info = userService.updateBaseUserInfo(request);
            ResultUtils.setSuccess(result, "修改成功", info);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/updatePwd")
    public BaseResult register(@RequestParam("userId") Long userId,
                               @RequestParam("oldPwd") String oldPwd,
                               @RequestParam("newPwd") String newPwd,
                               @RequestParam("confirmPwd") String confirmPwd) {

        BaseResult result = ResultUtils.result();
        try {
            userService.updateUserPwd(userId, oldPwd, newPwd, confirmPwd);
            ResultUtils.setSuccess(result, "修改成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 判断用户是否收藏了指定的菜谱
     */
    @GetMapping("/isCollect")
    public BaseResult isCollect(@RequestParam("userId") Long userId,
                                @RequestParam("cookId") Long cookId) {

        BaseResult result = ResultUtils.result();
        try {
            boolean isCollect = userService.isCollect(userId, cookId);
            ResultUtils.setSuccess(result, "请求成功", isCollect);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }


    /**
     * 为指定用户收藏指定菜谱，如果已经收藏则取消收藏
     */
    @PostMapping("/collectCook")
    public BaseResult collectCook(@RequestParam("userId") Long userId,
                                  @RequestParam("cookId") Long cookId) {

        BaseResult result = ResultUtils.result();
        try {
            userService.collectCook(userId, cookId);
            ResultUtils.setSuccess(result, "操作成功", ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

    /**
     * 获取指定用户收藏的所有菜谱
     */
    @GetMapping("/collectList")
    public BaseResult collectList(@RequestParam("userId") Long userId,
                                  @RequestParam("pageNum") Integer pageNum,
                                  @RequestParam("pageSize") Integer pageSize) {
        BaseResult result = ResultUtils.result();
        try {
            PageVO<CookBaseInfoVO> list = userService.getCollectList(userId, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", list);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }


    /**
     * 根据用户id获取用户参加的所有活动
     */
    @GetMapping("/activityList")
    public BaseResult activityList(@RequestParam("userId") Long userId,
                                   @RequestParam("pageNum") Integer pageNum,
                                   @RequestParam("pageSize") Integer pageSize) {
        BaseResult result = ResultUtils.result();
        try {
            PageVO<ActivityInfoVO> list = userService.getActivityList(userId, pageNum, pageSize);
            ResultUtils.setSuccess(result, "获取成功", list);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }


}
