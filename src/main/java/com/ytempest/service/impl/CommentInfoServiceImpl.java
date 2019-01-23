package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.CommentInfoMapper;
import com.ytempest.service.CommentInfoService;
import com.ytempest.vo.CommentInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

import javax.annotation.Resource;

@Service("CommentInfoService")
public class CommentInfoServiceImpl implements CommentInfoService {

    @Resource(name = "CommentInfoMapper")
    private CommentInfoMapper mapper;

    @Override
    public void addComment(CommentInfoVO comment) throws ServiceException {
        try {
            if (comment.getCommentFromUser().equals(comment.getCommentToUser())) {
                throw new ServiceException("不能自己评论自己");
            }
            mapper.insert(comment);
        } catch (SQLException e) {
            throw new ServiceException("评论失败");
        }
    }
}