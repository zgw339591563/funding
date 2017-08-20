package com.atguigu.test;

public class Test06 {

	public static void main(String[] args) {
		String s = "xxxx.xxx.png";
		
		String path = s.substring(s.lastIndexOf("."));
		
		System.out.println( path );
	}

}
