package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.ActivityInfoMapper;
import com.ytempest.service.ActivityInfoService;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserActivityPrizeVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

/**
 * @author ytempest
 * Description：
 */
@Service("ActivityInfoService")
public class ActivityInfoServiceImpl implements ActivityInfoService {

    private static final String TAG = "ActivityInfoServiceImpl";

    @Resource(name = "ActivityInfoMapper")
    private ActivityInfoMapper actMapper;

    @Override
    public PageVO<ActivityInfoVO> getActivityList(Integer pageNum, Integer pageSize) throws ServiceException {
        try {
            // 获取活动的记录总数
            long total = actMapper.countAll();
            // 计算总页面数
            int pageCount = (int) (total % pageSize == 0
                    ? total / pageSize
                    : total / pageSize + 1);
            // 判断输入的页码是否超过数据的页码范围
            if (pageNum < 1) {
                throw new ServiceException("页码数必须要大于等于1");
            }
            if (pageNum > pageCount) {
                throw new ServiceException("已经到底");
            }

            // 4、封装PageVO数据
            PageVO<ActivityInfoVO> pageVO = new PageVO<>(total, pageSize, pageNum,
                    pageCount);
            pageVO.setList(actMapper.selectList((pageNum - 1) * pageSize, pageSize));

            return pageVO;
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }

    @Override
    public ActivityInfoVO getActivityInfo(Long actId) throws ServiceException {
        try {
            return actMapper.selectDetailInfo(actId);
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }

    @Override
    public List<UserActivityPrizeVO> getAwardList(Long actId) throws ServiceException {
        try {
            return actMapper.selectAwardList(actId);
        } catch (SQLException e) {
            throw new ServiceException("获取失败");
        }
    }
}
