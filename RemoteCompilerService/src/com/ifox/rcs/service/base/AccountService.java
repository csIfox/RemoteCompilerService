package com.ifox.rcs.service.base;

/**
 * 账户操作接口
 * @author zh
 * @param <T> 账户对象
 */
public interface AccountService<T> {
	/**
	 * @param loginInfo 登陆信息
	 * @return
	 */
	T login(Object... loginInfo);
	
	/**
	 * 注册
	 * @return
	 */
	boolean register(T t);
}
