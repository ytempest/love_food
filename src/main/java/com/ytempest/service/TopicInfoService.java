package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.BaseTopicInfoVO;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ytempest
 */
public interface TopicInfoService {

    /**
     * 搜索从第pageNum页开始的数据
     */
    PageVO<TopicInfoVO> getTopicList(int pageNum, int pageSize)
            throws ServiceException;

    /**
     * 添加话题
     */
    void addTopic(BaseTopicInfoVO topic, HttpServletRequest request) throws ServiceException;

    /**
     * 根据话题Id获取该话题的所有评论列表
     */
    PageVO<TopicCommentInfoVO> getCommentListById(long topicId, int pageNum, int pageSize) throws Exception;

    List<TopicDetailCommentVO> getCommentInfo(Integer topicId, Integer commentId,
                                              Integer replyToUser) throws Exception;

    PageVO<TopicInfoVO> getUserTopicList(Long userId, Integer pageNum, Integer pageSize)
            throws ServiceException;
}
