package com.frw.learn.thread;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {


	public static void test1(){
		Thread t = new Thread(new Runnable()
		{
			private int count = 0;
			public void run()
			{
				long start = System.currentTimeMillis();
				long end = 0;

				while ((end - start) <= 1000)
				{
					count++;
					end = System.currentTimeMillis();
				}

				System.out.println("after 1 second.count=" + count);

				//等待或许许可
				LockSupport.park();
				System.out.println("thread over." + Thread.currentThread().isInterrupted());

			}
		});

		t.start();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 中断线程
		t.interrupt();


		System.out.println("main over");
	}

	
	public  static void test(){
		Thread thread = Thread.currentThread();

		LockSupport.unpark(thread);

		System.out.println("a");
		LockSupport.park();
		System.out.println("b");
		LockSupport.park();
		System.out.println("c");
	}
	
	
	public static void test2(){
		Thread thread = new Thread(){
			public void run(){
				System.out.println("before park");
				LockSupport.park();
				System.out.println("after park");
			}
		};
		
		thread.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main unpark");
		LockSupport.unpark(thread);
		
		
		
	}
	public static void main(String args[]){

		test2();
//		test();
	}
}
