package com.frw.dev.observer;

public class Adder {

	private Store store;

	public boolean isRun=true;

	
	private String name;
	public Adder(Store store,String name){
		this.store=store;
		this.name=name;
	}

	public void start(){
		Thread th=new Thread(){
			public void run(){
				while(isRun){
//					System.out.println(name+" run ");
					store.put(1);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};

		th.setName("TH_"+name);
		th.start();
	}



}
