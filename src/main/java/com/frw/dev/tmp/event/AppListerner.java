package com.frw.dev.tmp.event;


import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;


public class AppListerner implements ApplicationListener<ContextStartedEvent> {
	Logger log=Logger.getLogger(AppListerner.class);
	public void onApplicationEvent(ContextStartedEvent arg0) {

		System.out.println("!!!!!!!!! start  ContextStartedEvent");
		log.info("!!!!!!!!! start  ContextStartedEvent");
	}

}
