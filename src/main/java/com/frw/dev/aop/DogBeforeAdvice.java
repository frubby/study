package com.frw.dev.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.SQLException;

@Component
public class DogBeforeAdvice implements MethodBeforeAdvice, AfterReturningAdvice ,ThrowsAdvice,MethodInterceptor{

	Logger log = Logger.getLogger(DogBeforeAdvice.class);

	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {

		log.info("Before " + arg0.getName() + " WANG WANG");

	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		log.info("after " + method.getName() + " AOAO ");

	}

	public Object invoke(MethodInvocation arg0) throws Throwable {
		log.info("环绕  before.");
		Object obj=arg0.proceed();
		log.info("环绕  after.");
		return obj;
	}

	public void afterThrowing(SQLException e)  throws Throwable {
		log.error(" catch exception ");
		e.printStackTrace();
	}

}
