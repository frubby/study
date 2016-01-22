package com.frw.dev.aop;

import org.apache.log4j.Logger;


public class Dog implements Animal {
	Logger log=Logger.getLogger(Dog.class);
	public void run() {
		log.info("dog run");

		throw new RuntimeException("测试");
	}

	public void eat() {
		log.info("dog eat");

	}

	public void sleep() {
		log.info("dog sleep");

	}

	public void say() {
		log.info("dog  wang wang!");

	}

}
