package com.atguigu.atcrowdfunding.util;

public class StringUtil {

	public static boolean isEmpty( String content ) {
		return content == null || "".equals(content);
	}
	
	public static boolean isNotEmpty( String content ) {
		return !isEmpty(content);
	}
}
