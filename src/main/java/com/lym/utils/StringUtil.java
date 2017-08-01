package com.lym.utils;


public class StringUtil {

	public static boolean isEmpty(String... args) {
		for (String string : args) {
			if (string == null || string.length() <= 0) {
				return true;
			}
		}
		return false;
	}
	
	
}
