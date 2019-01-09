package com.ytempest.mapper;

import com.ytempest.vo.CommentInfoVO;
import com.ytempest.vo.TopicCommentInfoVO;
import com.ytempest.vo.TopicDetailCommentVO;
import com.ytempest.vo.TopicInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("CommentInfoMapper")
public interface CommentInfoMapper extends MapperSupport<CommentInfoVO> {
}
