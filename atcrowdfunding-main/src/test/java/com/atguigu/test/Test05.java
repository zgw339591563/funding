package com.atguigu.test;

public class Test05 {

	public static void main(String[] args) {
		
		// 
		int i = 1;
		//int j = i++;
		i = i++; // 将等号右边的计算结果赋值给等号左边
		// 临时变量 
		// i = _a; _a = i++; // _a = 1; i++ ==> 2 // i = _a = 1
		
		System.out.println( "i = " + i );
		//System.out.println( "j = " + j );
		
		//int pageno = 2;
		//changePageno(pageno--);
	}
	
	public static void changePageno( int pageno ) {
		System.out.println( pageno );
	}

}
