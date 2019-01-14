package com.ytempest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ytempest
 * Description：话题的图片信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicImageVO {

    private Long imageId;
    private Long topicId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;

    public TopicImageVO() {
    }

    public TopicImageVO(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "TopicImageVO{" +
                "imageId=" + imageId +
                ", topicId=" + topicId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
