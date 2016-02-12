package com.frw.learn.algorithm.sort.quick;

import java.util.Random;

public class GenerUtil {

	public static int[] genIntArr(int num){
		int []arr=new int[num];
		Random rd=new Random();
		for(int i=0;i<num;i++){
			arr[i]=rd.nextInt(10000000);
		}
		return arr;
	}

}
