package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.PageVO;

import java.sql.SQLException;

/**
 * @author ytempest
 * Description：
 */
public interface CookInfoService {
    PageVO<CookBaseInfoVO> getCookList(String cookGroup, String cookType,
                                       Integer pageNum, Integer pageSize) throws ServiceException, SQLException;

    CookDetailInfoVO getCookInfo(Long cookId) throws ServiceException;
}
