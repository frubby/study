package com.frw.lean.pattern.decorator;

public class RealDec2 extends Decorator{

	public RealDec2(Component comp){
		super(comp);
	}
	public void operate(){
		super.operate();
		System.out.println("Dec 2...");
	}
}
