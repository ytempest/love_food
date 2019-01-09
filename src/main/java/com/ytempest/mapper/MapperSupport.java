package com.ytempest.mapper;

import java.sql.SQLException;
import java.util.List;

public interface MapperSupport<T> {

	/**
	 * 新增一条记录
	 */
	void insert(T model) throws SQLException;

	/**
	 * 根据主键更新其它字段
	 */
	void updateById(T model) throws SQLException;

	/**
	 * 根据主键获取一条完整记录
	 */
	T selectById(String id) throws SQLException;

	/**
	 * 获取从第index条记录开始的len条记录
	 */
	List<T> selectAll(int index, int len) throws SQLException;

	/**
	 * 统计数量
	 */
	long countAll() throws SQLException;
}
