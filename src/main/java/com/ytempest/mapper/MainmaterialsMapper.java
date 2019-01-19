package com.ytempest.mapper;

import com.ytempest.vo.MainmaterialsVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("MainmaterialsMapper")
public interface MainmaterialsMapper extends MapperSupport<MainmaterialsVO> {

    void insertList(List<MainmaterialsVO> mainmaterials) throws SQLException;

    void delete(Long cookId) throws SQLException;
}
