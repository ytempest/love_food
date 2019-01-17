package com.ytempest.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ytempest.util.SecurityUtils;
import com.ytempest.exception.ServiceException;
import com.ytempest.mapper.AdminInfoMapper;
import com.ytempest.service.AdminInfoService;
import com.ytempest.vo.AdminInfoVO;

@Service("AdminInfoService")
public class AdminInfoServiceImpl implements AdminInfoService {

	@Resource(name = "AdminInfoMapper")
	private AdminInfoMapper adminInfoMapper;

	@Override
	public AdminInfoVO login(String account, String password)throws ServiceException {

		AdminInfoVO vo = adminInfoMapper.selectByAccount(account);
		String encryptedPassword = SecurityUtils.encrypt(password.trim());
		
		if(vo==null) {
			throw new ServiceException(ServiceException.LOGIN_ADMIN_NOEXIT, "该账号不存在");
		}else if(!encryptedPassword.equals(vo.getAdminPwd())) {
			throw new ServiceException(ServiceException.LOGIN_ADMIN_PWD_ERR, "密码错误");
		}

		return vo;
	}

}
