package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.PageVO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ytempest
 * Description：
 */
public interface CookInfoService {
    PageVO<CookBaseInfoVO> getCookList(String cookGroup, String cookType,
                                       Integer pageNum, Integer pageSize) throws ServiceException;

    CookDetailInfoVO getCookInfo(Long cookId) throws ServiceException;

    CookBaseInfoVO addCook(HttpServletRequest request) throws ServiceException;

    void deleteCook(Long cookId) throws ServiceException;

    void updateCook(HttpServletRequest request) throws ServiceException;

    /**
     * 根据活动Id获取参与活动的所有菜谱
     */
    PageVO<CookBaseInfoVO> getPartakeCookList(Long actId, Integer pageNum, Integer pageSize)
            throws ServiceException;

    /**
     * 根据用户Id获取参与活动的所有菜谱
     */
    PageVO<CookBaseInfoVO> getUserCookList(Long userId, Integer pageNum, Integer pageSize)
            throws ServiceException;
}
