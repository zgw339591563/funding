package com.atguigu.test;

public class Test03 {

	public static void main(String[] args) {
		
		String ss = null;
		
		System.out.println( ss );
		
        String s = "abc";
        System.out.println( "加密前：" + s );
        String s1 = encode1(s);
        System.out.println( "加密后：" + s1 );
        String s2 = decode1(s1);
        System.out.println( "解密后：" + s2 );
	}
	public static String decode( String s ) {
		StringBuilder builder = new StringBuilder();
		
		//byte[] bs = s.
	    char[] cs = s.toCharArray();
		for ( char c : cs ) {
			int i = c - 1;
			char c1 = (char)i;
			builder.append(c1);
		}
		
		return builder.toString();
	}
	public static String decode1( String s ) {
		StringBuilder builder = new StringBuilder();
		
		//byte[] bs = s.
	    char[] cs = s.toCharArray();
		for ( char c : cs ) {
			int i = c<<1;
			char c1 = (char)i;
			builder.append(c1);
		}
		
		return builder.toString();
	}
	public static String encode1( String s ) {
		StringBuilder builder = new StringBuilder();
		
		//byte[] bs = s.
	    char[] cs = s.toCharArray();
		for ( char c : cs ) {
			int i = c>>1;
			char c1 = (char)i;
			builder.append(c1);
		}
		
		return builder.toString();
	}
	public static String encode( String s ) {
		
		// StringBuffer
		// String
		StringBuilder builder = new StringBuilder();
		
		//byte[] bs = s.
	    char[] cs = s.toCharArray();
		for ( char c : cs ) {
			int i = c + 1;
			char c1 = (char)i;
			builder.append(c1);
		}
		
		return builder.toString();
	}

}
