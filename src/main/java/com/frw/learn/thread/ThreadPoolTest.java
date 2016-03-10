package com.frw.learn.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

	public static final int THREAD_NUM = 10000;

	public static class MyThread extends Thread {
		public MyThread(int i) {
			this.setName("TH_" + i);
		}

		public void run() {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.getName());
		}
	}

	public static void fixThreadPoolTest() {
		ExecutorService es = Executors.newFixedThreadPool(10);

		for (int i = 0; i < THREAD_NUM; i++) {
			es.submit(new MyThread(i));
		}
	}

	public static void cachedThreadPoolTest() {
		ExecutorService es = Executors.newCachedThreadPool();

		for (int i = 0; i < THREAD_NUM; i++) {
			es.submit(new MyThread(i));
		}
	}

	public static void myThreadPoolTestSyncQue() {
		SynchronousQueue bq = new SynchronousQueue();
		ThreadPoolExecutor es = new ThreadPoolExecutor(2, 6, 0, TimeUnit.SECONDS, bq, new RejectedExecutionHandler() {

			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// MyThread mth=(MyThread) r;
				System.out.println("rejected thread : " + r.toString());

			}

		});

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("submit : " + i);
			es.submit(new MyThread(i));
			System.out.println("pool : " + es.getPoolSize());
		}
		System.out.println("pool last: " + es.getPoolSize());
		while(es.getPoolSize()>es.getCorePoolSize()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("pool last: " + es.getPoolSize());
		}
	}
	public static void myThreadPoolTest() {
		BlockingQueue bq = new ArrayBlockingQueue(4);
		ThreadPoolExecutor es = new ThreadPoolExecutor(2, 4, 0, TimeUnit.SECONDS, bq, new RejectedExecutionHandler() {

			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// MyThread mth=(MyThread) r;
				System.out.println("rejected thread : " + r.toString());

			}

		});

		for (int i = 0; i < THREAD_NUM; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.print("submit : " + i+" ");
			es.submit(new MyThread(i));
			System.out.println("pool : "+i+"  "  + es.getPoolSize());
		}
	}

	public static void main(String args[]) {
		// fixThreadPoolTest();
		// cachedThreadPoolTest();
		//		myThreadPoolTest();
		myThreadPoolTestSyncQue();
	}

}
