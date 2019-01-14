package com.ytempest.mapper;

import com.ytempest.vo.TopicImageVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("TopicImageInfoMapper")
public interface TopicImageInfoMapper extends MapperSupport<TopicImageVO> {
    void insertList(List<TopicImageVO> imageList) throws SQLException;
}
