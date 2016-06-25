package com.frw.learn.algorithm.sort.quick;

public class QuickSort {
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

	public static void main(String[] args){
		int arr[]=GenerUtil.genIntArr(100000000);
		long start,end;
		start =System.currentTimeMillis();
		QuickSort.sort(arr, 0, arr.length-1);
		end =System.currentTimeMillis();

		//		System.out.println(Arrays.toString(arr));
		System.out.println("time: "+(end-start)+" ms");

	}

}
