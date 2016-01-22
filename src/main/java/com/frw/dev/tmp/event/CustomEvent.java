package com.frw.dev.tmp.event;

import org.springframework.context.ApplicationEvent;

public class CustomEvent extends  ApplicationEvent {

	public CustomEvent(Object source) {
		super(source);
	}


	public String toString(){
		return "custom event.";
	}

}
