package com.atguigu.test;

import java.util.Random;

public class RandomTest {

	public static void main(String[] args) {
		
		//Math.random() * 10 + 1;
		Random random = new Random();
		
		for ( int i = 0; i < 2; i++ ) {
			int j = random.nextInt(13);
			System.out.println( j );
		}
		
	}

}
