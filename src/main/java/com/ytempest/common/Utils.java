package com.ytempest.common;

import com.ytempest.vo.UserInfoVO;

import java.util.Date;

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

}
