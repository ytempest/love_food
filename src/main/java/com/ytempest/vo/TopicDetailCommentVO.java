package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：话题详情页每一个评论的信息
 */
public class TopicDetailCommentVO {
    private Long userId;
    private String userHeadUrl;
    private String userAccount;
    private String replyContent;
    private Date replyTime;

    public TopicDetailCommentVO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public String toString() {
        return "TopicDetailCommentVO{" +
                "userId=" + userId +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                '}';
    }
}