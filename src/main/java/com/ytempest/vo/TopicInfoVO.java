package com.ytempest.vo;

import java.util.Date;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public class TopicInfoVO {
    private Long topicId;
    private Long userId;
    private String userHeadUrl;
    private String userAccount;
    private String topicTitle;
    private String topicContent;
    private Date topicPublishTime;
    private List<TopicImageVO> topicImage;

    private Long commentCount;
    private Long zanCount;

    public TopicInfoVO() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getZanCount() {
        return zanCount;
    }

    public void setZanCount(Long zanCount) {
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
