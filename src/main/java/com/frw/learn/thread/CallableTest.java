package com.frw.learn.thread;

import java.util.concurrent.*;

public class CallableTest {

	class MyCaller implements Callable<String>{

		public String call() throws Exception {
			System.out.println("future  start ");
			Thread.sleep(2000);
			
			return "frw";
		}
		
		
	}
	
	
	public void testFutureWithThread(){
		FutureTask<String> future=new FutureTask<String>(new MyCaller());
		new Thread(future).start();
		System.out.println("main get start ");
		try {
			System.out.println("get :" +future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	
	}
	
	public void testFutureWithExecutor(){
		ExecutorService exe=Executors.newFixedThreadPool(1);
		Future<String> future= exe.submit(new MyCaller());
		System.out.println("main get start ");
		try {
			System.out.println("get :" +future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		exe.shutdown();
	}
	
	public void testFutureTaskWithExecutor(){
		ExecutorService exe=Executors.newFixedThreadPool(1);
		FutureTask<String> future=new FutureTask<String>(new MyCaller());
		exe.submit(future);
		System.out.println("main get start ");
		try {
			System.out.println("get :" +future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		exe.shutdown();
	}	
	
	public static void main(String args[]){
		CallableTest test=new CallableTest();
//		test.testFutureWithThread();
//		test.testFutureWithExecutor();
		test.testFutureTaskWithExecutor();
	}
	
	
}
