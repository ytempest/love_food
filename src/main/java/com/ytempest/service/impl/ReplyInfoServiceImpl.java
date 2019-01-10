package com.ytempest.service.impl;

import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.ReplyInfoMapper;
import com.ytempest.service.ReplyInfoService;
import com.ytempest.vo.ReplyInfoVO;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

import javax.annotation.Resource;

@Service("ReplyInfoService")
public class ReplyInfoServiceImpl implements ReplyInfoService {

    @Resource(name = "ReplyInfoMapper")
    private ReplyInfoMapper mapper;

    @Override
    public void addReply(ReplyInfoVO reply) throws ServiceException {
        try {
            mapper.insert(reply);
        } catch (SQLException e) {
            throw new ServiceException("插入失败");
        }
    }
}