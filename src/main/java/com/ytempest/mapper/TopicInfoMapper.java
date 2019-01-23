package com.ytempest.mapper;

import com.ytempest.vo.BaseTopicInfoVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("TopicInfoMapper")
public interface TopicInfoMapper extends MapperSupport<BaseTopicInfoVO> {

    List<TopicInfoVO> selectTopicList(int index, int len) throws SQLException;

    List<TopicCommentInfoVO> selectCommentListById(String id) throws SQLException;

    List<TopicDetailCommentVO> selectDetailComment(Integer topicId, Integer commentId,
                                                   Integer replyToUser);

    List<TopicInfoVO> selectUserTopicList(Long userId, Integer pageNum, Integer pageSize)
            throws SQLException;

    long countUserTopicList(Long userId) throws SQLException;
}
