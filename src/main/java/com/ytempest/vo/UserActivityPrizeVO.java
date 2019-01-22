package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class UserActivityPrizeVO extends PrizeVO {
    private Long awaId;
    private Long userId;
    private String userAccount;
    private Long cookId;
    private String cookName;

    public UserActivityPrizeVO() {
    }

    public Long getAwaId() {
        return awaId;
    }

    public void setAwaId(Long awaId) {
        this.awaId = awaId;
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

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getCookName() {
        return cookName;
    }

    public void setCookName(String cookName) {
        this.cookName = cookName;
    }

    @Override
    public String toString() {
        return "UserActivityPrizeVO{" +
                "userId=" + userId +
                ", userAccount='" + userAccount + '\'' +
                ", cookId=" + cookId +
                ", cookName='" + cookName + '\'' +
                '}';
    }
}
