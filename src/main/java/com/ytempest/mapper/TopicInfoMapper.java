package com.ytempest.mapper;

import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("TopicInfoMapper")
public interface TopicInfoMapper extends MapperSupport<TopicInfoVO> {
    List<TopicCommentInfoVO> selectCommentListById(String id) throws SQLException;

    List<TopicDetailCommentVO> selectDetailComment(Integer topicId, Integer commentId,
                                                   Integer replyToUser);
}
