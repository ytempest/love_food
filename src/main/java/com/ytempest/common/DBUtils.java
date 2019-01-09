package com.ytempest.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtils {

	private static ComboPooledDataSource dataSource = null;

	static {
		// 创建对象时会默认加载 src/下的c3p0-config.xml配置文件
		dataSource = new ComboPooledDataSource();
	}

	private DBUtils() {

	}

	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() throws SQLException {

		return dataSource.getConnection();
	}

	public static void close(Connection connection) {
		close(connection, null, null);
	}

	/**
	 * 关闭Connection、Statement
	 */
	public static void close(Connection connection, Statement state) {
		close(connection, state, null);
	}

	/**
	 * 关闭、Statement
	 */
	public static void close(Statement pState) {
		close(null, pState, null);
	}

	/**
	 * 关闭Connection、Statement、ResultSet
	 */
	public static void close(Connection connection, Statement state, ResultSet reset) {

		try {
			if (connection != null) {
				connection.close();
			}

			if (state != null) {
				state.close();
			}

			if (reset != null) {
				reset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}