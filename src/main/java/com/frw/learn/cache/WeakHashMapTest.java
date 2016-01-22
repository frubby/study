package com.frw.learn.cache;

import java.util.WeakHashMap;

public class WeakHashMapTest {

	public static void main(String[] args) { 
		WeakHashMap map=new WeakHashMap();
		for(int i=0;i<100000;i++){
			Integer ikey=i;
			map.put(ikey, new String("fuck_"+i));
			
		}
		System.gc();
		System.out.println(""+map.get(10000));
		System.out.println(""+map.get(0));
		
		
	}

}
