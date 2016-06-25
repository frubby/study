package com.frw.dev.app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.frw.dev.tmp.MessagePrinter;
import com.frw.dev.tmp.MessageService;

public class AppFactory {


	public static void main(String args[]){
		ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
		Resource res=resolver.getResource("classpath:tmp_bean.xml");
		BeanFactory beanFac=new XmlBeanFactory(res);

		MessageService ms=(MessageService)beanFac.getBean("infoMessageService");

		System.out.println(ms.getMessage());
		
		
		MessagePrinter printer=(MessagePrinter)beanFac.getBean("messagePrinter");

		
		printer.printMessage();
	}

}
