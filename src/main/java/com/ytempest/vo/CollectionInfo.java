package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class CollectionInfo {
    private Long collectId;
    private Long userId;
    private Long cookId;

    public CollectionInfo() {
    }

    public CollectionInfo(Long collectId, Long userId, Long cookId) {
        this.collectId = collectId;
        this.userId = userId;
        this.cookId = cookId;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
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
        return "CollectionInfo{" +
                "collectId=" + collectId +
                ", cookId=" + cookId +
                ", userId=" + userId +
                '}';
    }
}
