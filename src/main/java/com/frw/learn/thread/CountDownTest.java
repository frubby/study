package com.frw.learn.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownTest {

	
	public static class MyThread extends Thread{
		CountDownLatch latch;
		public MyThread(CountDownLatch latch){
			this.latch=latch;
		}
		@Override
		public void run() {
			try {
				Random rand=new Random();
				
				this.sleep(1000+rand.nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Thread "+Thread.currentThread().getId()+" finish");
			latch.countDown();
		}
		
		
	}
	
	
	
	public static void main(String args[]){
		
		int threadNum=10;
		CountDownLatch latch=new CountDownLatch(threadNum);
		
		
		for(int i=0;i<threadNum;i++){
			Thread th=new MyThread(latch);
			th.start();
		}
		

		System.out.println("wait for thread stop");
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	
}
