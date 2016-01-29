package com.frw.learn.thread;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static final int SEM_NUM=10;

	public static void main(String args[]){

		Semaphore sem=new Semaphore(SEM_NUM);
		for(int i=0;i<20;i++){
			Thread th=new GetThread(sem);
			th.start();
		}

	}



	static class GetThread extends Thread{
		Semaphore sem;
		public GetThread(Semaphore sem){
			this.sem=sem;
		}
		public void run(){
			try {
				System.out.println("in Thread "+Thread.currentThread().getId()+" free: "+sem.availablePermits());
				
				sem.acquire();
				System.out.println("get Thread "+Thread.currentThread().getId()+" free: "+sem.availablePermits());
				Random rad=new Random();
				Thread.sleep(1000+rad.nextInt(1000)*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("release Thread "+Thread.currentThread().getId());
			sem.release();
		}
	}


}
