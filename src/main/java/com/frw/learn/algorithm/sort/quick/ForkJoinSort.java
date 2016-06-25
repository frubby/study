package com.frw.learn.algorithm.sort.quick;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ForkJoinSort {

	static class SortTask extends RecursiveAction {

		int[] arr;
		int start;
		int end;
		public SortTask(int[] arr,int start,int end){
			this.arr=arr;
			this.start=start;
			this.end=end;
		}
		@Override
		protected void compute() {
			
			if(start>=end)
				return;
			else if(end-start<=100){
				sort(this.arr,this.start,this.end);
				return;
			}
			int b=start;
			int h=end;
			int comp=arr[end];
			while(b<h){
				while(arr[b]<=comp&&b<h){
					b++;
				}
				if(b<h){
					arr[h--]=arr[b];
				}
				while(arr[h]>=comp&&b<h){
					h--;
				}
				if(b<h){
					arr[b++]=arr[h];
				}
			}
			arr[b]=comp;
			
			SortTask t1=new SortTask(arr,start,b-1);
			SortTask t2=new SortTask(arr,b+1,end);
			this.invokeAll(t1,t2);

			
		}
		
		public static void sort(int arr[],int start,int end){

			if(start>=end)
				return;
			int b=start;
			int h=end;
			int comp=arr[end];
			while(b<h){
				while(arr[b]<=comp&&b<h){
					b++;
				}
				if(b<h){
					arr[h--]=arr[b];
				}
				while(arr[h]>=comp&&b<h){
					h--;
				}
				if(b<h){
					arr[b++]=arr[h];
				}
			}
			arr[b]=comp;
			sort(arr,start,b-1);
			sort(arr,b+1,end);
		}


		   
	}
	
	
	
	public static void main(String args[]){
		ForkJoinPool pool=new ForkJoinPool(5);
		int arr[]=GenerUtil.genIntArr(100000000);
		long start,end;
		SortTask task=new SortTask(arr, 0, arr.length-1);
		start =System.currentTimeMillis();
		pool.execute(task);
		
		while(!task.isDone()){
//			System.out.println(pool.getActiveThreadCount()+"  "+pool.getStealCount()+pool.getParallelism());
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pool.shutdown();

		end =System.currentTimeMillis();
		System.out.println("time: "+(end-start)+" ms");
		
//		System.out.println(Arrays.toString(arr));
		
		
		
		

	}
	
	
	
	
	
	
}
