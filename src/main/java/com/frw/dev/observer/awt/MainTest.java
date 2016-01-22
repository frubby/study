package com.frw.dev.observer.awt;

public class MainTest {

	public static void main(String[] args) {
		TheObserver theobj=new TheObserver();
		for(int i=0;i<10;i++){
			Watcher watcher =new Watcher("watcher "+i);
			theobj.addObserver(watcher); 
		}
		theobj.update();
	}

}
