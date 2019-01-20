package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class PartakeActivityVO {
    private Long partId;
    private Long actId;
    private Long cookId;
    private Long userId;

    public PartakeActivityVO() {
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getActId() {
        return actId;
    }

    public void setActId(Long actId) {
        this.actId = actId;
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PartakeActivityVO{" +
                "partId=" + partId +
                ", actId=" + actId +
                ", cookId=" + cookId +
                ", userId=" + userId +
                '}';
    }
}
