package com.ytempest.mapper;

import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.PartakeActivityVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author ytempest
 * Description：
 */
@Service("PartakeActivityMapper")
public interface PartakeActivityMapper extends MapperSupport<ActivityInfoVO> {
    PartakeActivityVO getPartakeUserInfo(Long userId, Long actId) throws SQLException;
}
