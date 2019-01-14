package com.ytempest.vo;

import java.util.Date;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public class BaseTopicInfoVO {
    private Long topicId;
    private Long userId;
    private String topicTitle;
    private String topicContent;
    private Date topicPublishTime;

    public BaseTopicInfoVO() {
    }

    public BaseTopicInfoVO(Long topicId, Long userId, String topicTitle, String topicContent, Date topicPublishTime) {
        this.topicId = topicId;
        this.userId = userId;
        this.topicTitle = topicTitle;
        this.topicContent = topicContent;
        this.topicPublishTime = topicPublishTime;
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



    @Override
    public String toString() {
        return "BaseTopicInfoVO{" +
                "topicId=" + topicId +
                ", userId=" + userId +
                ", topicTitle='" + topicTitle + '\'' +
                ", topicContent='" + topicContent + '\'' +
                ", topicPublishTime=" + topicPublishTime +
                '}';
    }
}
