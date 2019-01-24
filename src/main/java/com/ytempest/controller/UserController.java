package com.ytempest.controller;

import com.ytempest.service.CookInfoService;
import com.ytempest.service.TopicInfoService;
import com.ytempest.util.LogUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.UserInfoService;
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
     * 根据账号和手机号码登录
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
     */
    @PostMapping("/register")
    public BaseResult register(@RequestParam("account") String account,
                               @RequestParam("pwd") String pwd,
                               @RequestParam("phone") String phone) {

        BaseResult result = ResultUtils.result();
        try {
            UserInfoVO userInfo = UserInfoVO.baseUserInfo();
            userInfo.setUserAccount(account);
            userInfo.setUserPwd(pwd);
            userInfo.setUserPhone(phone);
            userInfo.setUserRegisterTime(new Date());
            userService.addUser(userInfo);
            LogUtils.e(TAG, "register: user=" + userInfo);
            ResultUtils.setSuccess(result, "注册成功", userInfo);
        } catch (Exception e) {
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
            if (e.getErrorCode() == ServiceException.USER_TOPIC_LIST_END) {
                ResultUtils.setError(result, "已经到底", ResultUtils.NullList);
            } else {
                ResultUtils.setError(result, e.getMessage(), ResultUtils.NullList);
            }
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
            ResultUtils.setSuccess(result,"修改成功",ResultUtils.NullObj);
        } catch (ServiceException e) {
            ResultUtils.setError(result, e.getMessage(), ResultUtils.NullObj);
        }
        return result;
    }

}
