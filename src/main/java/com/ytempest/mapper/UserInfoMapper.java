package com.ytempest.mapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ytempest.vo.UserInfoVO;

@Service("UserInfoMapper")
public interface UserInfoMapper extends MapperSupport<UserInfoVO> {

    /*
     * // 一对一的测试方法 UserInfoVO selectById(Long userId);
     *
     * // 一对多的测试方法 UserInfoVO selectListById(Long userId);
     *
     * int insertStudnet(UserInfoVO user);
     *
     * void updateStudent(UserInfoVO user);
     *
     */
    // List<UserInfoVO> selectByStudent(UserInfoVO user);

    UserInfoVO selectByAccount(String id) throws SQLException;

    List<UserInfoVO> fuzzySearchByAccount(String account, int index, int count);

    long countFuzzySearch(String account);

    /*----------- API -----------*/

    long countUserByPhone(String phone);

}
