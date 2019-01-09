package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.AdminInfoVO;

/**
 * 定义处理Servlet层数据的相关规范，负责将数据查询层DAO的数据进行相关处理，以得到能直接给Servlet层进行使用的数据规格
 *
 * @author ytempest
 */
public interface AdminInfoService {

    /**
     * 根据管理员账号和密码进行登录
     */
    AdminInfoVO login(String account, String password) throws ServiceException;
}
