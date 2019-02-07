package com.ytempest.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoVO {
    private Long userId;
    private String userAccount;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userPwd;
    private String userHeadUrl;
    private String userSex;
    private Date userBirth;
    private String userPhone;
    private String userEmail;
    private String userQQ;
    private Date userRegisterTime;
    private Integer userStatus;

    public static UserInfoVO baseUserInfo() {
        UserInfoVO vo = new UserInfoVO();
        vo.setUserHeadUrl("/user/default.png");
        vo.setUserSex("");
        // 默认时间戳为 2000-01-01 00:00:00
        vo.setUserBirth(new Date(946656000000L));
        vo.setUserEmail("");
        vo.setUserQQ("");
        vo.setUserStatus(0);
        return vo;
    }

    public UserInfoVO() {
        super();
    }

    public UserInfoVO(Long userId) {
        super();
        this.userId = userId;
    }

    public UserInfoVO(Long userId, String userAccount, String userPwd,
                      String userHeadUrl, String userSex, Date userBirth,
                      String userPhone, String userEmail, String userQQ,
                      Date userRegisterTime, Integer userStatus) {
        super();
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPwd = userPwd;
        this.userHeadUrl = userHeadUrl;
        this.userSex = userSex;
        this.userBirth = userBirth;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userQQ = userQQ;
        this.userRegisterTime = userRegisterTime;
        this.userStatus = userStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserQQ() {
        return userQQ;
    }

    public void setUserQQ(String userQQ) {
        this.userQQ = userQQ;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        String birth = null;
        if (userBirth != null) {
            birth = new SimpleDateFormat("yyyy-MM-dd").format(userBirth);
        }
        String registerTime = null;
        if (userRegisterTime != null) {
            registerTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(
                    userRegisterTime);
        }
        return "UserInfoVO [userId=" + userId + ", userAccount=" + userAccount
                + ", userPwd=" + userPwd + ", userHeadUrl=" + userHeadUrl
                + ", userSex=" + userSex + ", userBirth="
                + birth
                + ", userPhone=" + userPhone + ", userEmail=" + userEmail
                + ", userQQ=" + userQQ + ", userRegisterTime="
                + registerTime
                + ", userStatus=" + userStatus + "]";
    }


}