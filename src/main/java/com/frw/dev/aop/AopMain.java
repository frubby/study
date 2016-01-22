package com.frw.dev.aop;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopMain {
	static ApplicationContext context;
	public static void main(String[] args) {
		context=new ClassPathXmlApplicationContext("tmp_aop.xml");



		Animal animal=(Animal)context.getBean("dog");
		//animal.run();

		//		testAopByProxy();

		Animal  prox_anm=(Animal)context.getBean("dogproxy");
		try{
			prox_anm.run();
		}catch(Exception e){

		}
		//	prox_anm.eat();
	}


	public static void testAopByProxy(){
		Animal animal=(Animal)context.getBean("dog");
		BeforeAdvice advice;
		ProxyFactory pf;
		advice=new DogBeforeAdvice();
		pf=new ProxyFactory();
		pf.setTarget(animal);
		pf.addAdvice(advice);
		Animal anprox=(Animal)pf.getProxy();
		anprox.run();
		anprox.eat();
	}
}
