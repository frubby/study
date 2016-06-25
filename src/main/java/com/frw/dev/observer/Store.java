package com.frw.dev.observer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Store {

	Logger log=Logger.getLogger(Store.class);
	int size=10;
	private List<Integer> lists;
	public int getSum=0;
	public int addSum=0;
	public Store(){
		lists=new ArrayList<Integer>();
	}
	
	public List<Integer> getLists() {
		return lists;
	}

	public void setLists(List<Integer> lists) {
		this.lists = lists;
	}
	
	
	public  synchronized  void put(int i){
		while(lists.size()>=10){
			try {
//				System.out.println(Thread.currentThread().getName()+" wait start");
				this.wait(1000);
//				System.out.println(Thread.currentThread().getName()+" wait end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lists.add(i);
		System.out.println(Thread.currentThread().getName()+" put .  size:"+lists.size());
		addSum++;
		this.notifyAll();
	}
	
	public  synchronized  int get(){
		while(lists.size()<=0){
			try {
//				System.out.println(Thread.currentThread().getName()+" wait start");
				this.wait();
//				System.out.println(Thread.currentThread().getName()+" wait end");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int rel= lists.remove(lists.size()-1);
		System.out.println(Thread.currentThread().getName()+" get .  size:"+lists.size());
		getSum++;
		this.notifyAll();
		return rel;
		
	}
}
