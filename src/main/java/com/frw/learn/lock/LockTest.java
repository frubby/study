package com.frw.learn.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	ReentrantLock locker;
	private int count;
	public LockTest(){
		count=0;
		locker=new ReentrantLock();
	}

	public void add(int i){
		locker.lock();
		locker.lock();
		count=count+i;
		System.out.println("Thread "+Thread.currentThread().getId()+" add");
		locker.unlock();
		locker.unlock();
	}
	public int get(){
		return count;
	}

	public  static  void main(String[] args){
		LockTest lockTest=new LockTest();


		for(int i=0;i<1000;i++){
			Thread th=new Thread(new MyThread(lockTest,1000));
			th.setDaemon(true);
			th.start();
		}

		for(int i=0;i<1000;i++){

			System.out.println("get:"+lockTest.get());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}


	public static class MyThread implements Runnable{
		LockTest lockTest;
		int total;
		int count;
		public MyThread(LockTest lockTest,int total){
			this.total=total;
			this.lockTest=lockTest;
			count=0;

		}
		public void run() {
			for(int i=0;i<total;i++){
				lockTest.add(1);
				count++;
			}
		}

	}

}
