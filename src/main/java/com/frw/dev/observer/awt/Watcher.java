package com.frw.dev.observer.awt;

import java.util.Observable;
import java.util.Observer;

public class Watcher implements Observer {

	private String name;
	public Watcher(String name){
		this.name=name;
	}
	
	public void update(Observable arg0, Object arg1) {

			System.out.println(name+" "+" recv ");

	}

}
