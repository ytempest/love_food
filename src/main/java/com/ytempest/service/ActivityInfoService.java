package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserActivityPrizeVO;

import java.util.List;

/**
 * @author ytempest
 * Description：
 */
public interface ActivityInfoService {
    PageVO<ActivityInfoVO> getActivityList(Integer pageNum, Integer pageSize)
            throws ServiceException;

    ActivityInfoVO getActivityInfo(Long actId) throws ServiceException;

    List<UserActivityPrizeVO> getAwardList(Long actId) throws ServiceException;
}
