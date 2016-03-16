package com.ifox.rcs.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifox.rcs.bean.User;
import com.ifox.rcs.dao.IUserDao;
import com.ifox.rcs.dao.impl.UserDaoImpl;
import com.ifox.rcs.util.ValidateUtils;

/**
 * lym 检测手机号是否已存在
 */
public class IsPhoneExitServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		if (ValidateUtils.validatePhone(phone)) {
			IUserDao dao = new UserDaoImpl();
			User user = dao.getByPhone(phone);
			if (user == null) {
				setResult(200, null, "手机号可用");
			} else {
				setResult(400, null, "手机号已被绑定");
			}
			dao.closeDbc();
		} else {
			setResult(400, null, "手机号格式错误");
		}
		returnResult(response);
	}

}
