package com.atguigu.test;

public class Test02 {

	public static void main(String[] args) throws Exception {
		
		Thread t1 = new Thread();
		Thread t2 = new Thread();
		
		t1.start();
		t2.start();
		
		//t1.sleep(1000); // t1
		Thread.sleep(1000); // main线程休眠
		t2.wait(1000); // t2 等待
		
	}

}
