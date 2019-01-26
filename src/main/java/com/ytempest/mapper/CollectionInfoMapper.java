package com.ytempest.mapper;

import com.ytempest.vo.CollectionInfo;
import com.ytempest.vo.CookBaseInfoVO;
import com.ytempest.vo.CookDetailInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("CollectionInfoMapper")
public interface CollectionInfoMapper extends MapperSupport<CollectionInfo> {

    CollectionInfo selectBy(Long userId, Long cookId) throws SQLException;

    void deleteById(Long collectId) throws SQLException;
}
