package com.frw.dev.observer;

public class Getter {
	private Store store;
	  
	public boolean isRun=true;
	private String name;
	public Getter(Store store,String name){
		this.store=store;
		this.name=name;
	}

	public void  start(){
		Thread th=new Thread(new Runnable(){

			public void run() {
				while(isRun){
					int i=store.get();
//					System.out.println(Thread.currentThread().getName()+" get "+i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		th.setName("TH_GET_"+name);
		th.start();
	}


}
