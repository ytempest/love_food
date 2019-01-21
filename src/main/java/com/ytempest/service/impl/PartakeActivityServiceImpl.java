package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.PartakeActivityMapper;
import com.ytempest.service.PartakeActivityService;
import com.ytempest.vo.PartakeActivityVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

import javax.annotation.Resource;

/**
 * @author ytempest
 * Description：
 */
@Service("PartakeActivityService")
public class PartakeActivityServiceImpl implements PartakeActivityService {

    @Resource(name = "PartakeActivityMapper")
    private PartakeActivityMapper mapper;

    @Override
    public PartakeActivityVO isPartake(Long userId, Long actId) throws ServiceException {
        try {
            return mapper.getPartakeUserInfo(userId, actId);
        } catch (SQLException e) {
            throw new ServiceException("查询失败");
        }
    }

    @Override
    public void partakeActivity(PartakeActivityVO partake) throws ServiceException {
        try {
            mapper.insert(partake);
        } catch (SQLException e) {
            throw new ServiceException("参加失败，请重试");
        }
    }
}
