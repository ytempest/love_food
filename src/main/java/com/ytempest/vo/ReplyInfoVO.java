package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class ReplyInfoVO {
    private Long replyId;
    private Long commentId;
    private String replyContent;
    private Date replyTime;
    private Long replyFromUser;
    private Long replyToUser;

    public ReplyInfoVO() {
    }

    public ReplyInfoVO(Long replyId, Long commentId, String replyContent, Date replyTime, Long replyFromUser, Long replyToUser) {
        this.replyId = replyId;
        this.commentId = commentId;
        this.replyContent = replyContent;
        this.replyTime = replyTime;
        this.replyFromUser = replyFromUser;
        this.replyToUser = replyToUser;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    public Long getReplyFromUser() {
        return replyFromUser;
    }

    public void setReplyFromUser(Long replyFromUser) {
        this.replyFromUser = replyFromUser;
    }

    public Long getReplyToUser() {
        return replyToUser;
    }

    public void setReplyToUser(Long replyToUser) {
        this.replyToUser = replyToUser;
    }

    @Override
    public String toString() {
        return "ReplyInfoVO{" +
                "replyId=" + replyId +
                ", commentId=" + commentId +
                ", replyContent='" + replyContent + '\'' +
                ", replyTime=" + replyTime +
                ", replyFromUser=" + replyFromUser +
                ", replyToUser=" + replyToUser +
                '}';
    }
}
