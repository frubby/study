package com.frw.dev.tmp.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component("customPublisher")
public class CustomEventPublisher implements ApplicationEventPublisherAware {

	ApplicationEventPublisher publisher;
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {

		this.publisher=publisher;

	}

	public void publish(){
		CustomEvent ce=new CustomEvent(this);
		this.publisher.publishEvent(ce);
	}

}
