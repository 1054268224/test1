package com.cydroid.softmanager.rubbishcleaner.util;

public class StringFilterUtil {

	public static String filterAlphabet(String string) {
		String str = string;
		str = str.replaceAll("[^(A-Za-z)]", "");
		return str;
	}

	public static String getNumStr(String string) {
		String[] str = string.split(filterAlphabet(string));
		return str[0];
	}
}
