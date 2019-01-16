package com.ytempest.mapper;

import com.ytempest.vo.AdminInfoVO;

import org.springframework.stereotype.Service;

@Service("AdminInfoMapper")
public interface AdminInfoMapper {

	AdminInfoVO selectByAccount(String account);

}
