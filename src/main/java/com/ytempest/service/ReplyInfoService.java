package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.CommentInfoVO;
import com.ytempest.vo.ReplyInfoVO;

/**
 * @author ytempest
 */
public interface ReplyInfoService {
    void addReply(ReplyInfoVO reply) throws ServiceException;
}
