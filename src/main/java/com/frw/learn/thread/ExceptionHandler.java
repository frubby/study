package com.frw.learn.thread;

public class ExceptionHandler {

	
	
	static class MyThread extends Thread{
		public void run(){
			System.out.println("current id : "+Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				System.out.println("current id : "+Thread.currentThread().getName());
				Thread.currentThread().interrupt();
				//e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		
		MyThread th=new MyThread();
		th.start();
		th.interrupt();
		
		System.out.println(th.isInterrupted());
		
		
		
	}
	
	
}
