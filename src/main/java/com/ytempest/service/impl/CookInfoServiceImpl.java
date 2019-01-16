package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.CookInfoMapper;
import com.ytempest.service.CookInfoService;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;
import com.ytempest.vo.PageVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

import javax.annotation.Resource;

/**
 * @author ytempest
 * Description：
 */
@Service("CookInfoService")
public class CookInfoServiceImpl implements CookInfoService {

    @Resource(name = "CookInfoMapper")
    private CookInfoMapper mapper;

    @Override
    public PageVO<CookBaseInfoVO> getCookList(String cookGroup, String cookType,
                                              Integer pageNum, Integer pageSize) throws ServiceException, SQLException {
        // 获取用户的记录总数
        long total = mapper.countCookList(cookGroup, cookType);
        // 计算总页面数
        int pageCount = (int) (total % pageSize == 0
                ? total / pageSize
                : total / pageSize + 1);
        // 判断输入的页码是否超过数据的页码范围
        if (pageNum < 1) {
            throw new IllegalArgumentException(
                    "page number is out of page count");
        }
        if (pageNum > pageCount) {
            throw new ServiceException(ServiceException.COOK_LIST_END, "已经到底");
        }

        // 4、封装PageVO数据
        PageVO<CookBaseInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                pageCount);
        pageVO.setList(mapper.selectCookList(cookGroup, cookType,
                (pageNum - 1) * pageSize, pageSize));

        return pageVO;
    }

    @Override
    public CookDetailInfoVO getCookInfo(Long cookId) throws ServiceException {
        try {
            return mapper.selectCook(cookId);
        } catch (SQLException e) {
            throw new ServiceException("查询失败");
        }
    }
}
