package com.frw.learn.thread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class SimpleTest {


	public static void main(String[] args) {

		Callable<Integer> callable=new Callable<Integer>(){

			public Integer call() throws Exception {


				for(int i=0;i<6;i++){
					Thread.sleep(1000);
					System.out.println(Thread.currentThread().getId()+"  .. "+i);
				}

				return 1;
			}

		};



		FutureTask<Integer> future=new FutureTask<Integer>(callable);
		new Thread(future).start();
		System.out.println(Thread.currentThread().getId()+"  main  sleep");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+"  main  get");
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getId()+"  main end");
	}

}
