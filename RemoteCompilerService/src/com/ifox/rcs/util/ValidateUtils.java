package com.ifox.rcs.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	
	private static String FORMAT_USER_NAME = "([_a-zA-Z0-9][\\u4e00-\\u9fa5_a-zA-Z]{3,19})|([\\u4e00-\\u9fa5][\\u4e00-\\u9fa5_a-zA-Z]{1,19})";
	private static String FORMAT_PHONE = "[1][3|4|5|7|8][0-9]{9}";
	private static String FORMAT_PASSWORD = "[_a-zA-Z0-9]{6,20}";
	private static String FORMAT_DEVICE_TOKEN = "[a-zA-Z0-9]{32,128}";
	
	private static boolean validate(String format,String value){
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}

	public static boolean validateUserName(String userName) {
		return validate(FORMAT_USER_NAME, userName);
	}

	public static boolean validatePhone(String phone) {
		return validate(FORMAT_PHONE, phone);
	}

	public static boolean validatePassword(String password) {
		return validate(FORMAT_PASSWORD, password);
	}
	
	public static boolean validateDeviceToken(String deviceToken){
		return validate(FORMAT_DEVICE_TOKEN, deviceToken);
	}

}
