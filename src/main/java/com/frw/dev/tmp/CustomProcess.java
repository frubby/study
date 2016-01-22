package com.frw.dev.tmp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomProcess implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		if(arg1.equals("infoMessageService"))
			System.out.println("after "+arg1);
		return arg0;
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		// TODO Auto-generated method stub
		if(arg1.equals("infoMessageService"))
			System.out.println("before "+arg1);
		return arg0;
	}

}
