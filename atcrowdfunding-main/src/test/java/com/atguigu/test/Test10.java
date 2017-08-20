package com.atguigu.test;

public class Test10 {

	public static void main(String[] args) throws Exception {
		
		AAA aaa = new AAA();
		
		// 访问权限 ： 方法调用者和提供者之间的关系
		// clone提供者:com.atguigu.test.AAA
		// clone调用者:com.atguigu.test.Test10
		aaa.clone();
	}

}
class AAA {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}