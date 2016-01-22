package com.frw.dev.observer.awt;

import java.util.Observable;

public class TheObserver  extends Observable{

	
	public void update(){
		this.setChanged();
		this.notifyObservers();
	
	}
}
