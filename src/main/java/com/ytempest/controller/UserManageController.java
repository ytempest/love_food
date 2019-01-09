package com.ytempest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ytempest.common.SecurityUtils;
import com.ytempest.service.UserInfoService;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserInfoVO;

@Controller
@RequestMapping("/user")
public class UserManageController {

    @Resource(name = "UserInfoService")
    private UserInfoService service;

    /**
     * 获取用户列表
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView getUserList() {

        ModelAndView mav = new ModelAndView();

        PageVO<UserInfoVO> pageVO = null;
        try {
            pageVO = service.page(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        mav.addObject("pageInfo", pageVO);

        mav.setViewName("/manage/user_manage_list");

        return mav;
    }

    /**
     * 根据页码和关键字获取指定数量的用户
     */
    @RequestMapping(value = "pageList", method = RequestMethod.GET)
    @ResponseBody
    public PageVO<UserInfoVO> getPageTable(
            @RequestParam(value = "page", required = true, defaultValue = "1") String pageNoStr,
            @RequestParam(value = "key", required = true, defaultValue = "") String key) {

        PageVO<UserInfoVO> pageVO = null;
        try {
            if (!"".equals(key)) {
                pageVO = service.fuzzySearch(key, pageNoStr);
            } else {
                pageVO = service.page(pageNoStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pageVO;

    }

    /**
     * 根据用户名获取用户信息，然后然后返回jsp给前端
     */
    @RequestMapping(value = "preview", method = RequestMethod.GET)
    public ModelAndView previewUserInfo(
            @RequestParam(value = "account", required = true) String account) {

        ModelAndView mav = new ModelAndView();
        UserInfoVO previewUserInfo = null;
        try {
            previewUserInfo = service.selectByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("previewUserInfo", previewUserInfo);

        mav.setViewName("/manage/user_manage_preview_user");

        return mav;
    }

    /**
     * 根据用户名获取需要更新的用户信息
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    public ModelAndView getUpdateUserInfo(
            @RequestParam(value = "account", required = true) String account) {

        ModelAndView mav = new ModelAndView();
        UserInfoVO udpateUserInfo = null;
        try {
            udpateUserInfo = service.selectByAccount(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.addObject("udpateUserInfo", udpateUserInfo);

        mav.setViewName("/manage/user_manage_update_user");

        return mav;
    }


    /**
     * 检测用户是否已经存在，如果存在则返回相应的提示信息
     */
    @RequestMapping(value = "check", method = RequestMethod.GET)
    public void checkUserExist(
            @RequestParam(value = "userAccount") String userAccount,
            HttpServletResponse response) {

        try {
            UserInfoVO userInfo = service.selectByAccount(userAccount);
            if (userInfo != null) {
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().println("用户已存在");
            }
        } catch (Exception e) {

        }
    }

    /**
     * 添加用户
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ModelAndView addUser(
            @RequestParam(value = "userAccount") String userAccount,
            @RequestParam(value = "userHeadUrl") String userHeadUrl,
            @RequestParam(value = "userPwd") String userPwd,
            @RequestParam(value = "userSex") String userSex,
            @RequestParam(value = "userBirth") String userBirth,
            @RequestParam(value = "userPhone") String userPhone,
            @RequestParam(value = "userQQ") String userQQ,
            @RequestParam(value = "userEmail") String userEmail) {

        try {
            UserInfoVO userInfo = service.selectByAccount(userAccount);

            if (userInfo != null) {

            }

            if (!"".equals(userHeadUrl)) {
                userInfo.setUserHeadUrl(userHeadUrl);
            }

            if (!"".equals(userPwd)) {
                userPwd = SecurityUtils.encrypt(userPwd);
                userInfo.setUserPwd(userPwd);
            }

            userInfo.setUserRegisterTime(new Date());

            userInfo.setUserStatus(0);

            userInfo.setUserSex(userSex);

            if (!"".equals(userBirth)) {
                userInfo.setUserBirth(parseDateStr("yyyy-MM-dd", userBirth));
            }

            userInfo.setUserPhone(userPhone);

            userInfo.setUserQQ(userQQ);

            userInfo.setUserEmail(userEmail);

            service.updateUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getUserList();
    }

    /**
     * 根据用户名更新指定的用户
     */
    @RequestMapping(value = "confirm-update", method = RequestMethod.GET)
    public ModelAndView confirmUpdateUserInfo(
            @RequestParam(value = "userAccount") String userAccount,
            @RequestParam(value = "userHeadUrl") String userHeadUrl,
            @RequestParam(value = "userPwd") String userPwd,
            @RequestParam(value = "userRegisterTime") String userRegisterTime,
            @RequestParam(value = "userStatus") String userStatus,
            @RequestParam(value = "userSex") String userSex,
            @RequestParam(value = "userBirth") String userBirth,
            @RequestParam(value = "userPhone") String userPhone,
            @RequestParam(value = "userQQ") String userQQ,
            @RequestParam(value = "userEmail") String userEmail) {

        try {
            UserInfoVO userInfo = service.selectByAccount(userAccount);

            if (!"".equals(userHeadUrl)) {
                userInfo.setUserHeadUrl(userHeadUrl);
            }

            if (!"".equals(userPwd)) {
                userPwd = SecurityUtils.encrypt(userPwd);
                userInfo.setUserPwd(userPwd);
            }

            if (!"".equals(userRegisterTime)) {
                userInfo.setUserRegisterTime(
                        parseDateStr("yyyy-MM-dd HH:mm:ss", userRegisterTime));
            }

            userInfo.setUserStatus(Integer.parseInt(userStatus));

            userInfo.setUserSex(userSex);

            if (!"".equals(userBirth)) {
                userInfo.setUserBirth(parseDateStr("yyyy-MM-dd", userBirth));
            }

            userInfo.setUserPhone(userPhone);

            userInfo.setUserQQ(userQQ);

            userInfo.setUserEmail(userEmail);

            service.updateUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView mav = getUserList();
        mav.addObject("msg", "修改成功");

        return mav;
    }

    /**
     * 根据字符格式来格式化指定的日期字符串
     */
    public Date parseDateStr(String format, String dateStr) {
        try {
            return new SimpleDateFormat(format).parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

}
