package com.ifox.rcs.util;

import java.text.SimpleDateFormat;

public class TimeUtils {
	
	public static String getCurrentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = format.format(System.currentTimeMillis());
		return dateTime;
	}

}
