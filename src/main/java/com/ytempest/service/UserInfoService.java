package com.ytempest.service;

import com.ytempest.exception.ServiceException;
import com.ytempest.vo.PageVO;
import com.ytempest.vo.UserInfoVO;

import java.sql.SQLException;

/**
 * 定义处理Servlet层数据的相关规范，负责将数据查询层DAO的数据进行相关处理，以得到能直接给Servlet层进行使用的数据规格
 *
 * @author ytempest
 */
public interface UserInfoService {

    /**
     * 搜索从第pageNum页开始的数据
     */
    PageVO<UserInfoVO> page(String pageNum) throws Exception;

    /**
     * 根据key和roleId进行模糊查询，并将查询的结果按page进行分页显示
     *
     * @param key 用户名或者真实姓名
     */
    PageVO<UserInfoVO> fuzzySearch(String key, String pageStr) throws Exception;

    /**
     * 根据账号对用户进行锁定，如果用户已经锁定则将用户解锁
     */
    void lockUser(String account) throws Exception;

    /**
     * 添加用户
     */
    void addUser(UserInfoVO user) throws Exception;

    /**
     * 根据用户名搜索某个用户
     *
     * @return 如果搜索到就返回该用户，否则返回null
     */
    UserInfoVO selectByAccount(String account) throws Exception;

    /**
     * 根据用户名更新用户
     *
     * @param model 要更新的用户模型
     */
    void updateUser(UserInfoVO model) throws Exception;

    /* API */

    /**
     * 根据账号或者手机号、密码进行登录
     */
    UserInfoVO login(String account, String password) throws ServiceException;


}
