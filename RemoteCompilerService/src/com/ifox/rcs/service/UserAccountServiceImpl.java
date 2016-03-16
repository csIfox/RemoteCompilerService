package com.ifox.rcs.service;

import com.ifox.rcs.bean.User;
import com.ifox.rcs.dao.IUserDao;
import com.ifox.rcs.dao.impl.UserDaoImpl;
import com.ifox.rcs.service.base.AccountService;

/**
 * 用户账户服务操作实现类
 * @author zh
 *
 */
public class UserAccountServiceImpl implements AccountService<User>{

	private IUserDao dao;
	
	public UserAccountServiceImpl() {
		dao = new UserDaoImpl();
	}

	@Override
	public User login(Object... loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean register(User user) {
		dao.getByPhone(user.getPhone());
		boolean flag = dao.addUser(user);
		return false;
	}

}
