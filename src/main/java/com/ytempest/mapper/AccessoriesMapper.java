package com.ytempest.mapper;

import com.ytempest.vo.AccessoriesVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("AccessoriesMapper")
public interface AccessoriesMapper extends MapperSupport<AccessoriesVO> {

    void insertList(List<AccessoriesVO> accessories) throws SQLException;

    void delete(Long cookId) throws SQLException;

}
