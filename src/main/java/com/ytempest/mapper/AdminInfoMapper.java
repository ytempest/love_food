package com.ytempest.mapper;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ytempest.vo.AdminInfoVO;

@Service("AdminInfoMapper")
public interface AdminInfoMapper {

	AdminInfoVO selectByAccount(String account);

}
