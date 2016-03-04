package com.frw.learn.thread;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadException {

	class Mythread extends Thread{
		public void run(){
			
			int i=0;
			int j=1;
			int c=j/i;
//			try {
//				Thread.sleep(10000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				Thread.currentThread().interrupt();
//			}
		}
	}
	
	
	public void test(){
		Mythread th=new Mythread();
		th.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){

			public void uncaughtException(Thread t, Throwable e) { 
				System.out.println(t.getName());
				e.printStackTrace();
			}
			
		});
		th.start();
//		th.interrupt();
	}
	
	
	public static void main(String args[]){
		ThreadException th=new ThreadException();
		th.test();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("exit");
	}
	
	
	
}
