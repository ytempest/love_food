package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.TopicInfoMapper;
import com.ytempest.service.TopicInfoService;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

@Service("TopicInfoService")
public class TopicInfoServiceImpl implements TopicInfoService {

    private static final String TAG = "TopicInfoServiceImpl";

    @Resource(name = "TopicInfoMapper")
    private TopicInfoMapper mapper;

    @Override
    public PageVO<TopicInfoVO> getTopicList(int pageNum, int pageSize) throws ServiceException, SQLException {
        // 3、获取PageVO数据模型所需要的相关参数
        // 获取用户的记录总数
        long total = mapper.countAll();
        // 计算总页面数
        int pageCount = (int) (total % pageSize == 0
                ? total / pageSize
                : total / pageSize + 1);
        // 判断输入的页码是否超过数据的页码范围
        if (pageNum < 1) {
            throw new IllegalArgumentException(
                    "page number is out of page count");
        }
        if (pageNum > pageCount) {
            throw new ServiceException(ServiceException.TOPIC_LIST_END, "已经到底");
        }

        // 4、封装PageVO数据
        PageVO<TopicInfoVO> pageVO = new PageVO<TopicInfoVO>(total, pageSize, pageNum,
                pageCount);
        pageVO.setList(mapper.selectAll((pageNum - 1) * pageSize, pageSize));

        return pageVO;
    }


    @Override
    public void addTopic(TopicInfoVO topic) throws Exception {

    }

    @Override
    public List<TopicCommentInfoVO> getCommentListById(long topicId) throws Exception {
        return mapper.selectCommentListById(String.valueOf(topicId));
    }

    @Override
    public List<TopicDetailCommentVO> getCommentInfo(Integer topicId, Integer commentId,
                                                     Integer replyToUser) throws Exception {
        return mapper.selectDetailComment(topicId, commentId, replyToUser);
    }
}