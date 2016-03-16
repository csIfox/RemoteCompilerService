package com.ifox.rcs.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	private static Gson gson = new GsonBuilder().serializeNulls().create();
	
	public static String toJsonString(Object object) {
		return gson.toJson(object);
	}
}
