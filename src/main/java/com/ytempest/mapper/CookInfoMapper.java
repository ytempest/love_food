package com.ytempest.mapper;

import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("CookInfoMapper")
public interface CookInfoMapper extends MapperSupport<CookBaseInfoVO> {

    List<CookBaseInfoVO> selectCookList(String cookGroup, String cookType,
                                        Integer pageNum, Integer pageSize) throws SQLException;

    long countCookList(String cookGroup, String cookType) throws SQLException;

    CookDetailInfoVO selectCook(Long cookId) throws SQLException;

    void deleteCook(Long cookId) throws SQLException;

    List<CookBaseInfoVO> selectPartakeCookList(Long actId, Integer pageNum, Integer pageSize)
            throws SQLException;

    long countPartakeCookList(Long actId) throws SQLException;

    List<CookBaseInfoVO> selectUserCookList(Long userId, Integer pageNum, Integer pageSize)
            throws SQLException;

    long countUserCookList(Long userId) throws SQLException;
}
