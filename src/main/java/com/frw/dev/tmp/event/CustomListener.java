package com.frw.dev.tmp.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomListener implements ApplicationListener<CustomEvent>{

	public void onApplicationEvent(CustomEvent event) {
		System.out.println(event.toString());
		System.out.println("in event process "+ Thread.currentThread().getName());

	}

}
