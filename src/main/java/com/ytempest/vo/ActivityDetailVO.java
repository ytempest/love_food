package com.ytempest.vo;

import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public class ActivityDetailVO extends ActivityInfoVO {

    private List<PrizeVO> prizeList;
    private Long partakeCount;

    public ActivityDetailVO() {
    }

    public List<PrizeVO> getPrizeList() {
        return prizeList;
    }

    public void setPrizeList(List<PrizeVO> prizeList) {
        this.prizeList = prizeList;
    }

    public Long getPartakeCount() {
        return partakeCount;
    }

    public void setPartakeCount(Long partakeCount) {
        this.partakeCount = partakeCount;
    }

    @Override
    public String toString() {
        return "ActivityDetailVO{" +
                "prizeList=" + prizeList +
                ", partakeCount=" + partakeCount +
                '}';
    }
}
