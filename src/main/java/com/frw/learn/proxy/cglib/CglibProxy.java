package com.frw.learn.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor{
	private Object obj; 

	public Object CreateProxy(Object target){
		obj=target;
		Enhancer enhancer=new Enhancer();
		enhancer.setSuperclass(this.obj.getClass());
		enhancer.setCallback(this);
		enhancer.setClassLoader(target.getClass().getClassLoader());
		return enhancer.create();
		
	}

	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		Object result=null;
		System.out.println(" before ");
		
		result=arg3.invokeSuper(arg0, arg2);
		System.out.println(" after ");
		
		
		return result;
	}



}