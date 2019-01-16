package com.ytempest.vo;

import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public class CookDetailInfoVO extends CookBaseInfoVO {
    private String userHeadUrl;
    private String userAccount;
    private Long collectCount;

    private List<MainmaterialsVO> mainList;
    private List<AccessoriesVO> accList;
    private List<ProceduresVO> proceList;

    public CookDetailInfoVO() {
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Long getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public List<MainmaterialsVO> getMainList() {
        return mainList;
    }

    public void setMainList(List<MainmaterialsVO> mainList) {
        this.mainList = mainList;
    }

    public List<AccessoriesVO> getAccList() {
        return accList;
    }

    public void setAccList(List<AccessoriesVO> accList) {
        this.accList = accList;
    }

    public List<ProceduresVO> getProceList() {
        return proceList;
    }

    public void setProceList(List<ProceduresVO> proceList) {
        this.proceList = proceList;
    }

    @Override
    public String toString() {
        return "CookDetailInfoVO{" +
                "userHeadUrl='" + userHeadUrl + '\'' +
                ", userAccount=" + userAccount +
                ", collectCount=" + collectCount +
                ", mainList=" + mainList +
                ", accList=" + accList +
                ", proceList=" + proceList +
                '}';
    }
}
