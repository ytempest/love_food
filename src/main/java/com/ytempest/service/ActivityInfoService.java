package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.PageVO;

/**
 * @author ytempest
 * Description：
 */
public interface ActivityInfoService {
    PageVO<ActivityInfoVO> getActivityList(Integer pageNum, Integer pageSize)
            throws ServiceException;

    ActivityInfoVO getActivityInfo(Long actId) throws ServiceException;

    PageVO<CookBaseInfoVO> getPartakeCookList(Long actId, Integer pageNum, Integer pageSize)
            throws ServiceException;
}
