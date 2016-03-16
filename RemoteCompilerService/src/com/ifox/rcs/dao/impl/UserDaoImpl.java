package com.ifox.rcs.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ifox.rcs.bean.User;
import com.ifox.rcs.dao.IUserDao;
import com.ifox.rcs.util.LogUtils;

public class UserDaoImpl extends BaseDaoImpl implements IUserDao {

	private User resultSetToUserInfo(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getString("id"));
		user.setUserName(resultSet.getString("userName"));
		user.setPassword(resultSet.getString("password"));
		user.setPhone(resultSet.getString("phone"));
		user.setRegistTime(resultSet.getString("registTime"));
		user.setDeviceToken(resultSet.getString("deviceToken"));
		return user;
	}

	private List<User> getUser(String sql, String value) {
		List<User> list = new ArrayList<User>();
		if (initPstmt(sql)) {
			try {
				if (value != null) {
					getPstmt().setString(1, value);
				}
				ResultSet resultSet = getPstmt().executeQuery();
				while (resultSet.next()) {
					list.add(resultSetToUserInfo(resultSet));
				}
				return list;
			} catch (SQLException e) {
				LogUtils.e(e);
			} finally {
				closePstmt();
			}
		}
		return list;
	}

	private boolean update(String sql, String value, String id) {
		if (initPstmt(sql)) {
			int flag = 0;
			try {
				getPstmt().setString(1, value);
				getPstmt().setString(2, id);
				flag = getPstmt().executeUpdate();
			} catch (SQLException e) {
				LogUtils.e(e);
				return false;
			} finally {
				closePstmt();
			}
			if (flag == 0) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addUser(User user) {
		String sql = "insert into user_info (userName,password,phone,registTime,deviceToken)values (?,?,?,?,?)";
		if (initPstmt(sql)) {
			try {
				getPstmt().setString(1, user.getUserName());
				getPstmt().setString(2, user.getPassword());
				getPstmt().setString(3, user.getPhone());
				getPstmt().setString(4, user.getRegistTime());
				getPstmt().setString(5, user.getDeviceToken());
				int flag = getPstmt().executeUpdate();
				if (flag > 0) {
					return true;
				}
			} catch (SQLException e) {
				LogUtils.e(e);
			} finally {
				closePstmt();
			}
		}
		return false;
	}

	@Override
	public User getById(String id) {
		String sql = "select * from user_info where id=?";
		List<User> list = getUser(sql, id);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User getByPhone(String phone) {
		String sql = "select * from user_info where phone=?";
		List<User> list = getUser(sql, phone);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<User> getByUserName(String userName) {
		String sql = "select * from user_info where userName=?";
		return getUser(sql, userName);
	}

	@Override
	public List<User> getByRegistDate(String registDate) {
		String sql = "select * from user_info where phone like '?%'";
		return getUser(sql, registDate);
	}

	@Override
	public List<User> getAll() {
		String sql = "select * from user_info";
		return getUser(sql, null);
	}

	@Override
	public boolean updateUserName(String id, String userName) {
		String sql = "update user_info set userName = ? where id = ? ";
		return update(sql, userName, id);
	}

	@Override
	public boolean updatePassword(String id, String password) {
		String sql = "update user_info set password = ? where id = ? ";
		return update(sql, password, id);
	}

	@Override
	public boolean updatePhone(String id, String phone) {
		String sql = "update user_info set phone = ? where id = ? ";
		return update(sql, phone, id);
	}

	@Override
	public boolean updateDeviceToken(String id, String deviceToken) {
		String sql = "update user_info set deviceToken = ? where id = ? ";
		return update(sql, deviceToken, id);
	}

}
