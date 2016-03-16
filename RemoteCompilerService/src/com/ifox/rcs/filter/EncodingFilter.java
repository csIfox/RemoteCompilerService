package com.ifox.rcs.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ifox.rcs.util.LogUtils;
import com.ifox.rcs.util.PathUtils;

public class EncodingFilter implements Filter {

	private String reqEncoding;
	private String respEncoding;
	public static final String TAG = "EncodingFilter";

	@Override
	public void destroy() {
		reqEncoding = null;
		respEncoding = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		LogUtils.i(TAG, "设置前的编码参数-- reqEncoding " + request.getCharacterEncoding() + " respEncoding " + response.getCharacterEncoding());
		if (this.reqEncoding != null && !this.reqEncoding.isEmpty()) {
			request.setCharacterEncoding(reqEncoding);
		}

		if (this.respEncoding != null && !this.respEncoding.isEmpty()) {
			response.setCharacterEncoding(respEncoding);
		}
		LogUtils.i(TAG, "设置后的编码参数-- reqEncoding " + request.getCharacterEncoding() + " respEncoding " + response.getCharacterEncoding());

		filterChain.doFilter(request, response);
	}


	@Override
	public void init(FilterConfig config) throws ServletException {
		// 读取在xml中配置的编码参数
		reqEncoding = config.getInitParameter("reqEncoding");
		respEncoding = config.getInitParameter("respEncoding");
		initUserCodePath(config);
		LogUtils.i(TAG, "编码参数-- reqEncoding " + reqEncoding + " respEncoding " + respEncoding);
	}
	
	public void initUserCodePath(FilterConfig config){
		String realPath = config.getServletContext().getRealPath("/");
		File file = new File(new File(realPath).getParent()+File.separator+"userCode");
		if(!file.exists()){
			file.mkdirs();
		}
		PathUtils.setUserCodePath(file.toString());
	}

}
