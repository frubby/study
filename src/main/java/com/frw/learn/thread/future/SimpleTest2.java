package com.frw.learn.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SimpleTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Callable<Integer> callable=new Callable<Integer>(){
			public Integer call() throws Exception {
				for(int i=0;i<6;i++){
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getId()+"  .. "+i);
				}
				return 1;
			}
		};
		ExecutorService threadPool =Executors.newSingleThreadExecutor();
		Future<Integer> future=threadPool.submit(callable);
		
		
		System.out.println(Thread.currentThread().getId()+"  main  sleep");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getId()+"  main  get");
		try {
			System.out.println("rel: "+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+"  main end");
	}

}
