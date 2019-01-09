package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.CommentInfoVO;

/**
 * @author ytempest
 */
public interface CommentInfoService {
    void addComment(CommentInfoVO comment) throws ServiceException;
}
