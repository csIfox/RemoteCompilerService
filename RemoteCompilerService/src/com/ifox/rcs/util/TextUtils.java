package com.ifox.rcs.util;

public class TextUtils {

	public static boolean isEmpty(String s) {
		if (s == null) {
			return true;
		} else if ("".equals(s)) {
			return true;
		} else {
			return false;
		}
	}

}
