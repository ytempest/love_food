package com.ytempest.vo;

import java.util.Date;

/**
 * @author ytempest
 * Description：
 */
public class ActivityInfoVO {
    private Long actId;
    private String actImageUrl;
    private String actTitle;
    private String actDesc;
    private Date actStartTime;
    private Date actFinishTime;
    private String actRequest;

    public ActivityInfoVO() {
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public String getActImageUrl() {
        return actImageUrl;
    }

    public void setActImageUrl(String actImageUrl) {
        this.actImageUrl = actImageUrl;
    }

    public String getActTitle() {
        return actTitle;
    }

    public void setActTitle(String actTitle) {
        this.actTitle = actTitle;
    }

    public String getActDesc() {
        return actDesc;
    }

    public void setActDesc(String actDesc) {
        this.actDesc = actDesc;
    }

    public Date getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(Date actStartTime) {
        this.actStartTime = actStartTime;
    }

    public Date getActFinishTime() {
        return actFinishTime;
    }

    public void setActFinishTime(Date actFinishTime) {
        this.actFinishTime = actFinishTime;
    }

    public String getActRequest() {
        return actRequest;
    }

    public void setActRequest(String actRequest) {
        this.actRequest = actRequest;
    }

    @Override
    public String toString() {
        return "ActivityInfoVO{" +
                "actId=" + actId +
                ", actImageUrl='" + actImageUrl + '\'' +
                ", actTitle='" + actTitle + '\'' +
                ", actDesc='" + actDesc + '\'' +
                ", actStartTime=" + actStartTime +
                ", actFinishTime=" + actFinishTime +
                ", actRequest='" + actRequest + '\'' +
                '}';
    }
}
