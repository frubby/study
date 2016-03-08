package com.frw.learn.thread;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {

	static SynchronousQueue sq=new SynchronousQueue();
	
	
	static void  testPut(){	
		new Thread(){
			public void run(){
				try {
					System.out.println("start put");
					sq.put("test");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	static void  testTake(final int mark){	
		new Thread(){
			public void run(){
				try {
					System.out.println("start get"+ " "+mark);
					String str=(String) sq.take();
					System.out.println("get : "+ str+ " "+mark);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	static void  testRemove(){	
		new Thread(){
			public void run(){
				try {
					System.out.println("start remove");
					String str=(String) sq.take();
					System.out.println("get : "+ str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	public static void main(String args[]){
		testPut();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testTake(0);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("step 2");
		testTake(1);
		testTake(2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		testPut();
	}
	
	
	
	
	
	
}
