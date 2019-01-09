package com.ytempest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ytempest
 * Description：话题的图片信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicImageVO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageUrl;

    public TopicImageVO() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
