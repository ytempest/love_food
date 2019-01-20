package com.ytempest.vo;

/**
 * @author ytempest
 * Description：
 */
public class PrizeVO {
    private Long prizeId;
    private String prizeName;
    private String prizePrize;

    public PrizeVO() {
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizePrize() {
        return prizePrize;
    }

    public void setPrizePrize(String prizePrize) {
        this.prizePrize = prizePrize;
    }

    @Override
    public String toString() {
        return "PrizeVO{" +
                "prizeId=" + prizeId +
                ", prizeName='" + prizeName + '\'' +
                ", prizePrize='" + prizePrize + '\'' +
                '}';
    }
}
