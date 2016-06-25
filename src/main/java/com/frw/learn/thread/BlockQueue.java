package com.frw.learn.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueue {

	BlockingQueue<String> bqueue=new LinkedBlockingQueue<String>(10);

	class MyThread extends Thread{
		public void run(){
			int i=0;
			while(true){
				try {
					i++;
					System.out.println(i);
					bqueue.put("Thread");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void test(){
		new MyThread().start();
	
		try {
			Thread.sleep(1000);
			bqueue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		bqueue.s
	}
	public static void main(String[] args) {

		BlockQueue bq=new BlockQueue();
		bq.test();

		Executor s=Executors.newFixedThreadPool(10);
		
//ArrayDeque

	}

}
