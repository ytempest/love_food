package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class MainmaterialsVO {
    private Long mainId;
    private Long cookId;
    private String mainName;
    private String mainAmount;

    public MainmaterialsVO() {
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getCookId() {
        return cookId;
    }

    public void setCookId(Long cookId) {
        this.cookId = cookId;
    }

    public String getMainName() {
        return mainName;
    }

    public void setMainName(String mainName) {
        this.mainName = mainName;
    }

    public String getMainAmount() {
        return mainAmount;
    }

    public void setMainAmount(String mainAmount) {
        this.mainAmount = mainAmount;
    }

    @Override
    public String toString() {
        return "MainmaterialsVO{" +
                "mainId=" + mainId +
                ", cookId=" + cookId +
                ", mainName='" + mainName + '\'' +
                ", mainAmount='" + mainAmount + '\'' +
                '}';
    }
}
