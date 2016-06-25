package com.frw.dev.pc.ctl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PcMain {


	public static void main(String args[]){

		ApplicationContext context = new ClassPathXmlApplicationContext("pc_bean.xml");

		PcCtl pcctl=(PcCtl) context.getBean("pcCtl");
		pcctl.getPcMem();

		((AbstractApplicationContext)context).registerShutdownHook();
	}
}
