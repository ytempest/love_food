package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.PartakeActivityVO;

/**
 * @author ytempest
 * Description：
 */
public interface PartakeActivityService {
    PartakeActivityVO isPartake(Long userId, Long actId) throws ServiceException;
}
