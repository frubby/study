package com.frw.dev.tmp;

public class ErrorMessageService implements MessageService {

	public String getMessage() {
		// TODO Auto-generated method stub
		return "error message";
	}
	public void init(){
		System.out.println("error init");
	}
}
