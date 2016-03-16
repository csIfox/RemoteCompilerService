package com.ifox.rcs.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ifox.rcs.dao.IBaseDao;
import com.ifox.rcs.dbc.DatabaseConnection;
import com.ifox.rcs.dbc.factory.DatabaseConnectionFactory;
import com.ifox.rcs.util.LogUtils;

public class BaseDaoImpl implements IBaseDao {

	private DatabaseConnection dbc;
	private PreparedStatement pstmt;

	public BaseDaoImpl() {
		this.dbc = DatabaseConnectionFactory.getInstance();
		;
	}

	public boolean initPstmt(String sql) {
		LogUtils.i("java.sql.PreparedStatement", "初始化");
		try {
			pstmt = dbc.getConnection().prepareStatement(sql);
			return true;
		} catch (SQLException e) {
			LogUtils.e(e);
			closePstmt();
			return false;
		}
	}

	public void closePstmt() {
		LogUtils.i("java.sql.PreparedStatement", "销毁");
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				LogUtils.e(e);
			} finally {
				pstmt = null;
			}
		}
	}

	public PreparedStatement getPstmt() {
		return pstmt;
	}

	@Override
	public void closeDbc() {
		dbc.close();
	}

}
