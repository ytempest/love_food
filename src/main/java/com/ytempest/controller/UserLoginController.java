package com.ytempest.controller;

import com.ytempest.util.LogUtils;
import com.ytempest.util.ResultUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.service.UserInfoService;
import com.ytempest.vo.BaseResult;
import com.ytempest.vo.UserInfoVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserLoginController {
    private static final String TAG = "UserLoginController";

    @Resource(name = "UserInfoService")
    private UserInfoService service;

    /**
     * 根据账号和手机号码登录
     */
    @PostMapping("/login")
    public BaseResult login(@RequestParam("account") String account,
                            @RequestParam("password") String password) {
        BaseResult result = ResultUtils.result();

        try {
            UserInfoVO userInfoVO = service.login(account, password);
            ResultUtils.setSuccess(result, "登录成功", userInfoVO);
        } catch (ServiceException e) {
            String msg = e.getMessage();
            ResultUtils.setError(result, msg, ResultUtils.NullObj);
        } catch (SQLException e) {
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
            UserInfoVO userInfo = service.selectByAccount(account);
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
            service.addUser(userInfo);
            LogUtils.e(TAG, "register: user=" + userInfo);
            ResultUtils.setSuccess(result, "注册成功", userInfo);
        } catch (Exception e) {
            ResultUtils.setError(result, "注册失败，请重试", ResultUtils.NullObj);
        }
        return result;
    }
}
