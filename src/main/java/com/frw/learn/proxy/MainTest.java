package com.frw.learn.proxy;

import java.lang.reflect.Proxy;

public class MainTest {

	public static void main(String args[]){
		RealSubject relsub=new RealSubject();
		Subject ProxySub=(Subject)Proxy.newProxyInstance(Subject.class.getClassLoader(), 
				  new Class[]{Subject.class}, new ProxyHandler(relsub));
		ProxySub.doSomething();
		
	}
	

}
