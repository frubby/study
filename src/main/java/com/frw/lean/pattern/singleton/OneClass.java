package com.frw.lean.pattern.singleton;

//public class OneClass {
//	private static OneClass instance=new OneClass();
//	private OneClass(){
//		
//	}
//	public static OneClass getInstance(){
//		return instance;
//	}
//	public void func(){
//		System.out.println(this.toString());
//	}
//	
//	public static void main(String args[]){
//		OneClass one=OneClass.getInstance();
//		one.func();
//	}
//}


public class OneClass {
	private static OneClass instance=null;
	private OneClass(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public  static OneClass getInstance(){
		if(instance==null){
			synchronized(OneClass.class){
				if(instance==null){
					instance=new OneClass();
				}
			}
		}
		return instance;
	}
	public void func(){
		System.out.println(this.toString());
	}

	public static void main(String args[]){
		new Thread(new Runnable(){
			public void run() {
				OneClass two=OneClass.getInstance();
				two.func();
			}
		}).start();;
		OneClass one=OneClass.getInstance();
		one.func();



	}
}