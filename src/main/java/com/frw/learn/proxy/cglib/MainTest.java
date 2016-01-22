package com.frw.learn.proxy.cglib;

import com.frw.learn.proxy.RealSubject;

public class MainTest {

	public  static void main(String args[]){
		
		RealSubject reb=new RealSubject();
		CglibProxy proxy=new CglibProxy();
		RealSubject rebProxy=(RealSubject)proxy.CreateProxy(reb);
		rebProxy.doSomething();
	}
}
