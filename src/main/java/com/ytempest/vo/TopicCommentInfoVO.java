package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：话题详情页每一个评论的信息
 */
public class TopicCommentInfoVO {
    private Long commentId;
    private Long commentFromUser;
    private String userHeadUrl;
    private String userAccount;
    private String commentContent;
    private Date commentTime;
    private Long replyCount;

    public TopicCommentInfoVO() {
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getCommentFromUser() {
        return commentFromUser;
    }

    public void setCommentFromUser(Long commentFromUser) {
        this.commentFromUser = commentFromUser;
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

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Long replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "TopicCommentInfoVO{" +
                "commentId=" + commentId +
                ", commentFromUser=" + commentFromUser +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", replyCount=" + replyCount +
                '}';
    }
}
