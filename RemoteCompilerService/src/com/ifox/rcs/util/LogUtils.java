package com.ifox.rcs.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
	
	private static Logger nowLogger;
	
	public static Logger getLogger(String tag) {
		if(nowLogger == null || !nowLogger.getName().equals(tag)) {
			nowLogger = LogManager.getLogger(tag);
		}
		return nowLogger;
	}
	
	/**
	 * 记录调试日志
	 * @param tag 类标记
	 * @param t 异常信息
	 */
	public static synchronized void d(String tag, String msg) {
		getLogger(tag).debug(msg);
	}
	
	/**
	 * 记录错误日志
	 * @param tag 类标记
	 * @param msg 错误消息
	 * @param t 异常信息
	 */
	public static synchronized void e(String tag, String msg, Throwable t) {
		getLogger(tag).error(msg, t);
	}
	
	/**
	 * 记录错误日志
	 * @param t 异常信息
	 */
	public static synchronized void e(Throwable t) {
		String tag = t.getClass().getName();
		String msg = t.getMessage();
		getLogger(tag).error(msg, t);
	}
	
	/**
	 * 记录错误日志
	 * @param tag 类标记
	 * @param t 异常信息
	 */
	public static synchronized void e(String tag, String msg) {
		getLogger(tag).error(msg);
	}

	/**
	 * 记误信息日志
	 * @param tag 类标记
	 * @param t 异常信息
	 */
	public static synchronized void i(String tag, String msg) {
		getLogger(tag).info(msg);
	}

	/**
	 * 记录故障日志
	 * @param tag 类标记
	 * @param t 异常信息
	 */
	public static synchronized void f(String tag, String msg) {
		getLogger(tag).fatal(msg);
	}
	/**
	 * 记录警告日志
	 * @param tag 类标记
	 * @param t 异常信息
	 */
	public static synchronized void w(String tag, String msg) {
		getLogger(tag).warn(msg);
	}
	
}
