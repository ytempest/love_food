package com.ytempest.mapper;

import com.ytempest.vo.ProceduresVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author ytempest
 * Description：
 */
@Service("ProceduresMapper")
public interface ProceduresMapper extends MapperSupport<ProceduresVO> {
    void insertList(List<ProceduresVO> procedures) throws SQLException;
}
