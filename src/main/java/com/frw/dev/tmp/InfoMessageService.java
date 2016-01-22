package com.frw.dev.tmp;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

public class InfoMessageService implements MessageService ,InitializingBean{

	private String tag;
	private int id;

	public InfoMessageService(){
		System.out.println("InfoMessageService()  construct");
	}

	public String getMessage() {

		return "Info message";
	}
	public void init(){
		System.out.println("Info init");
	}

	public void destroy(){
		System.out.println("destroy init");
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("Info afterPropertiesSet init");

	}

	public String getTag() {
		return tag;
	}

	@Required
	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getId() {
		return id;
	}
	@Required
	public void setId(int id) {
		this.id = id;
	}
}
