package com.frw.learn.thread;

public class WaitBlockTest {
	final Object lock = new Object();
	int shnum=0;
	static final int MAX=6;

	public void simulate(){
		for(long l=0;l<10000000000L;l++){

		}
	}
	class MyThreadA extends Thread{
		public void run() {
			while(true){
				synchronized (lock) {
					while(shnum<1){
						try {
							System.out.println("thread A wait start "+Thread.currentThread().getName());
							lock.wait();
							System.out.println("thread A wait END "+Thread.currentThread().getName());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					simulate();
					shnum--;
					System.out.println("thread A get num "+shnum+"  "+Thread.currentThread().getName());
					lock.notify();
				}
			}
		}
	}

	class MyThreadB extends Thread{
		public void run() {
			while(true){
				synchronized (lock) {
					while(shnum>MAX){
						try {
							System.out.println("thread B wait start "+Thread.currentThread().getName());
							lock.wait();
							System.out.println("thread B wait END "+Thread.currentThread().getName());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					simulate();
					shnum++;
					System.out.println("thread B get num "+shnum+"  "+Thread.currentThread().getName());
					lock.notify();
				}
				simulate();
			}

		}
	}	

	class MyThreadNotify extends Thread{
		public void run() {
			synchronized (lock) {
				lock.notifyAll();
			}

		}
	}
	class MyThreadWait extends Thread{
		public void run() {
			//while(true){
			synchronized (lock) {
				try {
					System.out.println("thread A wait start "+Thread.currentThread().getName());
					lock.wait();
					System.out.println("thread A wait END "+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			simulate();
			//			try {
			//				this.sleep(20000);
			//			} catch (InterruptedException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
			//			}
			System.out.println("thread A finish    "+Thread.currentThread().getName());
			//		lock.notify();
			//	}
			while (true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	

	public void testWait(){

		for(int i=0;i<2;i++){
			new MyThreadWait().start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new MyThreadNotify().start();
	}
	public void test(){

		MyThreadB thB=new MyThreadB();
		for(int i=0;i<30;i++){
			new MyThreadA().start();
		}

		thB.start();
	}



	public void testIOwait(){
		new Thread(){
			public void run(){
				
			}
		}.start(); 


	}
	public static void main(String args[]) {
		WaitBlockTest test=new WaitBlockTest();
		test.testIOwait();


		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
