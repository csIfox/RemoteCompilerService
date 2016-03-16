package com.ifox.rcs.util;

import java.io.File;

public class PathUtils {
	
	private static String userCodePath;
	
	public static String getUserCodePath() {
		return userCodePath;
	}



	public static void setUserCodePath(String userCodePath) {
		PathUtils.userCodePath = userCodePath;
		LogUtils.i("用户代码存储路径", userCodePath);
	}



	public static String getUserPath(String[] paths){
		StringBuffer userPath = new StringBuffer(userCodePath);
		for (String string : paths) {
			userPath.append(File.separator).append(string);
		}
		return userPath.toString();
	}

}
