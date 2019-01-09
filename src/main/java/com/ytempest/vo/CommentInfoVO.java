package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class CommentInfoVO {
    private Long commentId;
    private Long topicId;
    private String commentContent;
    private Date commentTime;
    private Long commentFromUser;
    private Long commentToUser;

    public CommentInfoVO() {
    }

    public CommentInfoVO(Long commentId, Long topicId, String commentContent, Date commentTime, Long commentFromUser, Long commentToUser) {
        this.commentId = commentId;
        this.topicId = topicId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.commentFromUser = commentFromUser;
        this.commentToUser = commentToUser;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
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

    public Long getCommentFromUser() {
        return commentFromUser;
    }

    public void setCommentFromUser(Long commentFromUser) {
        this.commentFromUser = commentFromUser;
    }

    public Long getCommentToUser() {
        return commentToUser;
    }

    public void setCommentToUser(Long commentToUser) {
        this.commentToUser = commentToUser;
    }

    @Override
    public String toString() {
        return "CommentInfoVO{" +
                "commentId=" + commentId +
                ", topicId=" + topicId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", commentFromUser=" + commentFromUser +
                ", commentToUser=" + commentToUser +
                '}';
    }
}
