package com.ifox.rcs.dbc.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ifox.rcs.dbc.DatabaseConnection;
import com.ifox.rcs.util.LogUtils;

public class MySQLDatabaseConnection implements DatabaseConnection {

	private static String DB_URL = "java:comp/env/jdbc/remote_compiler";

	private Connection connection;

	public MySQLDatabaseConnection() {
		LogUtils.i("com.ifox.rcs.dbc.DatabaseConnection", "初始化");
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(DB_URL);
			connection = dataSource.getConnection();
		} catch (NamingException e) {
			LogUtils.e(e);
			close();
		} catch (SQLException e) {
			LogUtils.e(e);
			close();
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void close() {
		LogUtils.i("com.ifox.rcs.dbc.DatabaseConnection", "销毁");
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LogUtils.e(e);
			} finally {
				connection = null;
			}
		}
	}
}
