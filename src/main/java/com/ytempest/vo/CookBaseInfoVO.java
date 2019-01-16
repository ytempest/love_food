package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class CookBaseInfoVO {
    private Long cookId;
    private String cookGroup;
    private String cookType;
    private String cookImageUrl;
    private Long cookUserId;
    private String cookTitle;
    private String cookDesc;
    private Date cookPublishTime;

    public CookBaseInfoVO() {
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getCookGroup() {
        return cookGroup;
    }

    public void setCookGroup(String cookGroup) {
        this.cookGroup = cookGroup;
    }

    public String getCookType() {
        return cookType;
    }

    public void setCookType(String cookType) {
        this.cookType = cookType;
    }

    public String getCookImageUrl() {
        return cookImageUrl;
    }

    public void setCookImageUrl(String cookImageUrl) {
        this.cookImageUrl = cookImageUrl;
    }

    public Long getCookUserId() {
        return cookUserId;
    }

    public void setCookUserId(Long cookUserId) {
        this.cookUserId = cookUserId;
    }

    public String getCookTitle() {
        return cookTitle;
    }

    public void setCookTitle(String cookTitle) {
        this.cookTitle = cookTitle;
    }

    public String getCookDesc() {
        return cookDesc;
    }

    public void setCookDesc(String cookDesc) {
        this.cookDesc = cookDesc;
    }

    public Date getCookPublishTime() {
        return cookPublishTime;
    }

    public void setCookPublishTime(Date cookPublishTime) {
        this.cookPublishTime = cookPublishTime;
    }

    @Override
    public String toString() {
        return "CookBaseInfoVO{" +
                "cookId=" + cookId +
                ", cookGroup='" + cookGroup + '\'' +
                ", cookType='" + cookType + '\'' +
                ", cookImageUrl='" + cookImageUrl + '\'' +
                ", cookUserId=" + cookUserId +
                ", cookTitle='" + cookTitle + '\'' +
                ", cookDesc='" + cookDesc + '\'' +
                ", cookPublishTime=" + cookPublishTime +
                '}';
    }
}
