package com.ytempest.mapper;

import com.ytempest.vo.ActivityDetailVO;
import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.UserActivityPrizeVO;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("ActivityInfoMapper")
public interface ActivityInfoMapper extends MapperSupport<ActivityInfoVO> {

    List<ActivityInfoVO> selectList(Integer pageNum, Integer pageSize) throws SQLException;

    ActivityDetailVO selectDetailInfo(Long actId) throws SQLException;

    List<UserActivityPrizeVO> selectAwardList(Long actId) throws SQLException;
}
