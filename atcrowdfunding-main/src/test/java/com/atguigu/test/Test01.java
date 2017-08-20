package com.atguigu.test;

public class Test01 {

	public static void main(String[] args) {
		String s = " a b ";
		
		// 去掉字符串,，的首尾（半角）空格
		s = s.trim();
		
		//  String不可变字符串
		System.out.println( "!"+s+"!" );
	}

}
