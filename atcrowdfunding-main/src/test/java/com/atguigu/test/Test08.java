package com.atguigu.test;

import java.util.Timer;
import java.util.TimerTask;

public class Test08 {

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				System.out.println("打铃了!每过1秒一次");
			}
			
		}, 3000, 1000);
	}

}
