package com.frw.dev.app;

import com.frw.dev.tmp.MessagePrinter;
import com.frw.dev.tmp.event.CustomEventPublisher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{


		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("tmp_bean.xml");
		context.start();
		MessagePrinter msgPrint=(MessagePrinter) context.getBean("messagePrinter");
		msgPrint.printMessage();
		msgPrint=(MessagePrinter) context.getBean("messagePrinter");
		msgPrint.printMessage();
		
		
		CustomEventPublisher publisher =(CustomEventPublisher)context.getBean("customPublisher");
		publisher.publish();
		publisher.publish();
		((AbstractApplicationContext)context).registerShutdownHook();
		
		
		
		
		
	}


}
