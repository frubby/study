package com.frw.learn.algorithm.sort.quick;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiQuickSort {
	public static final int thread_num=10;
	private ThreadPoolExecutor exes;
	private BlockingQueue<Runnable> que;
	private int[] arr;

	public MultiQuickSort(int[] arr){
		this.arr=arr;
		que=new LinkedBlockingQueue<Runnable>();
		exes=new ThreadPoolExecutor(thread_num, thread_num, 0, TimeUnit.MINUTES, que);
	}

	public void  sort(final int start,final int end){
		int b=start;
		int h=end;
		int comp=arr[end];
		while(b<h){
			while(arr[b]<=comp&&b<h){
				b++;
			}
			if(b<h){
				arr[h]=arr[b];
				h--;
			}
			while(arr[h]>=comp&&b<h){
				h--;
			}
			if(b<h){
				arr[b]=arr[h];
				b++;
			}
		}
		arr[b]=comp;

		final int end_tmp=b-1;
		if(end_tmp>start){
//			exes.execute(new Runnable(){
//				public void run() {
					sort(start,end_tmp);
//				}
//			});
		}
		final int start_tmp=b+1;
		if(start_tmp<end){
			exes.execute(new Runnable(){
				public void run() {
					sort(start_tmp,end);
				}
			});
		}
	}

	public void sort(){
		sort(0,arr.length-1);
		while (true) {
			if(exes.getActiveCount()<=0&&exes.getCompletedTaskCount()==exes.getTaskCount()){
				exes.shutdown();
				break;
			}
		}
		try {
			exes.awaitTermination(1, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}


	public static void main(String[] args){
		int arr[]=GenerUtil.genIntArr(100000000);
		long start,end;
		MultiQuickSort msort=new MultiQuickSort(arr);
		start =System.currentTimeMillis();
		msort.sort();
		end =System.currentTimeMillis();

		System.out.println("time: "+(end-start)+" ms");
		//		System.out.println(Arrays.toString(arr));

	}

}
