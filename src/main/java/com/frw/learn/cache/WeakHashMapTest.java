package com.frw.learn.cache;

import java.util.WeakHashMap;

public class WeakHashMapTest {

	public static void main(String[] args) { 
		WeakHashMap map=new WeakHashMap();
		for(int i=0;i<100000;i++){
			Integer ikey=Integer.valueOf(i);
			map.put(ikey, new String("fuck_"+i));
			
		}
		System.gc();
		System.out.println(""+map.get(10000));
		System.out.println(""+map.get(0));
		
		
		
		Integer i1=1;
		Integer i2=new Integer(1);
		Integer i3=new Integer(1);
		Integer i4=2;
		Integer i5=i2+i3;
		System.out.println((i1==i2)+" "+(i2==i3)+" "+(i4==i5));  
		
	}

}
