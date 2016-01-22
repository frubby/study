package com.frw.dev.tmp;

public class DebugMessageService implements MessageService {

	public String getMessage() {
		// TODO Auto-generated method stub
		return "debug message";
	}


	public void init(){
		System.out.println("debug init");
	}

}
