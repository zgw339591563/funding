package com.atguigu.test;

public class Test07 {

	public static void main(String[] args) {
		
		int number = 5;
        int result = Integer.highestOneBit((number - 1) << 1);
        
        System.out.println( result );
	}

}
