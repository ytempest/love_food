package com.ytempest.common;

import com.ytempest.vo.UserInfoVO;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static UserInfoVO baseUserInfo() {
        UserInfoVO vo = new UserInfoVO();
        vo.setUserHeadUrl("/head/default.png");
        vo.setUserSex("");
        // 默认时间戳为 2000-01-01 00:00:00
        vo.setUserBirth(new Date(946656000000L));
        vo.setUserEmail("");
        vo.setUserQQ("");
        vo.setUserStatus(0);
        return vo;
    }

    public static String getProjectPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath(".");
    }

    public static String getProjectPath(ServletContextEvent contextEvent) {
        return contextEvent.getServletContext().getRealPath(".");
    }

    public static String getTopicImageDir(HttpServletRequest request) {
        return getProjectPath(request) + File.separator + Configure.TOPIC_IMAGE_DIR;
    }

}
