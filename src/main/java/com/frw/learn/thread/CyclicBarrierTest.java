package com.frw.learn.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static final int threadNum=10;
	public CyclicBarrier barrier;


	public CyclicBarrierTest(){
		barrier=new CyclicBarrier(threadNum,new Runnable(){

			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ready go "+Thread.currentThread().getId());
				
			}
			
		});
	}

	public static void main(String[] args) {
		CyclicBarrierTest test=new CyclicBarrierTest();

		for(int j=0;j<1;j++){
			for(int i=0;i<threadNum;i++){
				Thread th=new MyThread(test.barrier);
				th.start();
			}
//			try {
//				int left=test.barrier.await();
//				System.out.println("wait Main "+Thread.currentThread().getId()+" left :"+left);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (BrokenBarrierException e) {
//				e.printStackTrace();
//			}
		}
	}


	static class MyThread extends Thread{
		CyclicBarrier barrier;
		public MyThread(CyclicBarrier barrier){
			this.barrier=barrier;
		}
		@Override
		public void run() {
			Random rad=new Random();
			try {
				Thread.sleep(1000+rad.nextInt(1000)*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread "+Thread.currentThread().getId());

			try {
				int i=barrier.await();
				System.out.println("wait Thread "+Thread.currentThread().getId()+" left :"+i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000+rad.nextInt(10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		//	System.out.println("END Thread "+Thread.currentThread().getId());
		}

	}

}
