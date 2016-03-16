package com.ifox.rcs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifox.rcs.bean.User;
import com.ifox.rcs.dao.IUserDao;
import com.ifox.rcs.dao.impl.UserDaoImpl;
import com.ifox.rcs.util.LogUtils;
import com.ifox.rcs.util.TimeUtils;
import com.ifox.rcs.util.ValidateUtils;

/**
 * lym 用户注册servlet
 */
public class RegistServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String deviceToken = request.getParameter("deviceToken");
		LogUtils.i("注册信息", "password: " + password + "    phone: " + phone + "    deviceToken: " + deviceToken);
		if (!ValidateUtils.validatePassword(password)) {
			setResult(400, null, "密码格式错误");
		} else if (!ValidateUtils.validatePhone(phone)) {
			setResult(400, null, "手机号格式错误");
		} else if (!ValidateUtils.validateDeviceToken(deviceToken)) {
			setResult(400, null, "deviceToken格式错误");
		} else {
			// 设置code data msg
			String registTime = TimeUtils.getCurrentTime();
			LogUtils.i("注册时间", registTime);
			String userName = "用户" + phone.substring(5);
			User user = new User(null, userName, password, phone, registTime, deviceToken);
			IUserDao dao = new UserDaoImpl();
			if (dao.getByPhone(phone) != null) {
				setResult(400, null, "手机号重复");
			} else if (dao.addUser(user)) {
				user = dao.getByPhone(phone);
				user.setPassword(null);
				user.setDeviceToken(null);
				setResult(200, user, "注册成功");
			} else {
				setResult(400, null, "注册失败");
			}
			dao.closeDbc();
		}
		returnResult(response);
	}
}
