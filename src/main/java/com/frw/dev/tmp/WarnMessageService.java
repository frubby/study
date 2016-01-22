package com.frw.dev.tmp;

public class WarnMessageService implements MessageService {

	public String getMessage() {
		// TODO Auto-generated method stub
		return "warning message";
	}
	public void init(){
		System.out.println("warning init");
	}
}
