package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class ProceduresVO {
    private Long proceId;
    private Long cookId;
    private Integer proceNo;
    private String proceImageUrl;
    private String proceDesc;

    public ProceduresVO() {
    }

    public Long getProceId() {
        return proceId;
    }

    public void setProceId(Long proceId) {
        this.proceId = proceId;
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public Integer getProceNo() {
        return proceNo;
    }

    public void setProceNo(Integer proceNo) {
        this.proceNo = proceNo;
    }

    public String getProceImageUrl() {
        return proceImageUrl;
    }

    public void setProceImageUrl(String proceImageUrl) {
        this.proceImageUrl = proceImageUrl;
    }

    public String getProceDesc() {
        return proceDesc;
    }

    public void setProceDesc(String proceDesc) {
        this.proceDesc = proceDesc;
    }

    @Override
    public String toString() {
        return "ProceduresVO{" +
                "proceId=" + proceId +
                ", cookId=" + cookId +
                ", proceNo=" + proceNo +
                ", proceImageUrl='" + proceImageUrl + '\'' +
                ", proceDesc='" + proceDesc + '\'' +
                '}';
    }
}
