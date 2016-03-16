package com.ifox.rcs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import com.ifox.rcs.bean.JsonResult;
import com.ifox.rcs.util.GsonUtils;


/**
 * Servlet implementation class BaseServlet
 */
public abstract class BaseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;  

	private JsonResult result;

	private Map<String, Object> resultParams;
	
	public BaseServlet() {
		result = new JsonResult();
	}
	
	public void setcode(int code) {
		result.setCode(code);
	}
	
	public void setData(Object data) {
		result.setData(data);
		resultParams = null;
	}
	
	/**
	 * 设置结果对象为map
	 * @param key
	 * @param value
	 */
	public void setData(String key, Object value) {
		if(resultParams == null) {
			resultParams = new HashMap<String, Object>();
			result.setData(resultParams);
		}
		resultParams.put(key, value);
	}
	
	public void setMsg(String msg) {
		result.setMsg(msg);
	}
	
	public void setResult(int code, Object data, String msg) {
		setcode(code);
		setData(data);
		setMsg(msg);
	}
	
	/**
	 * @param response 将结果写入响应流
	 * @throws IOException
	 */
	public void returnResult(HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		response.getWriter().print(GsonUtils.toJsonString(result));
	}
}
