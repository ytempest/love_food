package com.ytempest.vo;

import java.util.Date;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public class TopicInfoVO {
    private long topicId;
    private long userId;
    private String userHeadUrl;
    private String userAccount;
    private String topicTitle;
    private String topicContent;
    private Date topicPublishTime;
    private List<TopicImageVO> topicImage;

    private long commentCount;
    private long zanCount;

    public TopicInfoVO() {
    }

    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Date getTopicPublishTime() {
        return topicPublishTime;
    }

    public void setTopicPublishTime(Date topicPublishTime) {
        this.topicPublishTime = topicPublishTime;
    }

    public List<TopicImageVO> getTopicImage() {
        return topicImage;
    }

    public void setTopicImage(List<TopicImageVO> topicImage) {
        this.topicImage = topicImage;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getZanCount() {
        return zanCount;
    }

    public void setZanCount(long zanCount) {
        this.zanCount = zanCount;
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

    @Override
    public String toString() {
        return "TopicInfoVO{" +
                "topicId=" + topicId +
                ", userId=" + userId +
                ", userHeadUrl='" + userHeadUrl + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicPublishTime=" + topicPublishTime +
                ", topicImage=" + topicImage +
                ", commentCount=" + commentCount +
                ", zanCount=" + zanCount +
                '}';
    }
}
