package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class AccessoriesVO {
    private Long accId;
    private Long cookId;
    private String accName;
    private String accAmount;

    public AccessoriesVO() {
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccAmount() {
        return accAmount;
    }

    public void setAccAmount(String accAmount) {
        this.accAmount = accAmount;
    }

    @Override
    public String toString() {
        return "AccessoriesVO{" +
                "accId=" + accId +
                ", cookId=" + cookId +
                ", accName='" + accName + '\'' +
                ", accAmount='" + accAmount + '\'' +
                '}';
    }
}
