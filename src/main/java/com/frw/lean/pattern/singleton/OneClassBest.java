package com.frw.lean.pattern.singleton;

public class OneClassBest {

	private OneClassBest(){
		
	}
	private void test(){
		
	}
	private static class ClassHandle{
		private  static OneClassBest oneInstace=new OneClassBest();
	}
	public   static OneClassBest getInstance(){
		return ClassHandle.oneInstace;
	}
	public static void main(String args[]){
		
		OneClassBest one =OneClassBest.getInstance();
		one.test();
		OneClassBest.ClassHandle cl=new OneClassBest.ClassHandle();
	}
}
