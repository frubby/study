package com.frw.dev.tmp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component("messagePrinter")
public class MessagePrinter {
	public static int INIT_COUNT=0;
	//	@Autowired
	//	@Qualifier("infoMessageService")
	private MessageService service;




	public MessagePrinter(){
		INIT_COUNT++;
		System.out.println("MessagePrinter() "+INIT_COUNT);
		//		service=null;
	}

	@Autowired(required=false)
	public MessagePrinter(@Qualifier("infoMessageService")MessageService infoMessageService) {
		INIT_COUNT++;
		System.out.println("MessagePrinter(args) "+INIT_COUNT);
		this.service = infoMessageService;
	}

	public void printMessage() {
		System.out.println(this.service.getMessage());
	}

	public MessageService getService() {
		return service;
	}

	public void setService(MessageService service) {
		this.service = service;
	}
}
