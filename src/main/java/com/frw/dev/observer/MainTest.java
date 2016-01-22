package com.frw.dev.observer;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String args[]){

		Store store=new Store();

		
		List<Adder> addlist =new ArrayList<Adder>();
		List<Getter> getlist=new ArrayList<Getter>();
		
		int addNum=10;
		for(int i=0;i<addNum;i++){
			Adder adder=new Adder(store,"A"+i);
			addlist.add(adder);
			adder.start();
		}
		int getNum=10;
		for(int i=0;i<getNum;i++){
			Getter getter=new Getter(store,"G"+i);
			getlist.add(getter);
			getter.start();
		}
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			for(Adder adder: addlist ){
				adder.isRun=false;
			}
		}
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			for(Getter getter: getlist ){
				getter.isRun=false;
			}
		}
		

		System.out.println("totoal add : "+store.addSum+"   get:"+store.getSum);
		
	}

}
