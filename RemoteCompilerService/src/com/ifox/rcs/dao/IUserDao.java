package com.ifox.rcs.dao;

import java.util.List;

import com.ifox.rcs.bean.User;

/**
 * @author zh 用户数据库操作接口
 */
public interface IUserDao extends IBaseDao {

	public abstract boolean addUser(User user);

	public abstract User getById(String id);

	public abstract User getByPhone(String phone);

	public abstract List<User> getByUserName(String userName);

	public abstract List<User> getByRegistDate(String registDate);

	public abstract List<User> getAll();

	public abstract boolean updateUserName(String id, String userName);

	public abstract boolean updatePassword(String id, String password);

	public abstract boolean updatePhone(String id, String phone);

	public abstract boolean updateDeviceToken(String id, String deviceToken);
}
