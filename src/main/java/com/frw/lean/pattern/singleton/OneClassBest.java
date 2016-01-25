package com.frw.lean.pattern.singleton;

public class OneClassBest {
	private OneClassBest(){
	}
	private static class ClassHandle{
		private  static OneClassBest oneInstace=new OneClassBest();
	}
	public   static OneClassBest getInstance(){
		return ClassHandle.oneInstace;
	}
}
