package com.ytempest.mapper;

import com.ytempest.vo.ActivityInfoVO;
import com.ytempest.vo.AdminInfoVO;

import org.springframework.stereotype.Service;

@Service("AdminInfoMapper")
public interface AdminInfoMapper extends MapperSupport<ActivityInfoVO> {

	AdminInfoVO selectByAccount(String account);

}
