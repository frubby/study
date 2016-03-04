package com.frw.lean.pattern.decorator;

public class RealDec1 extends Decorator{

	public RealDec1(Component comp){
		super(comp);
	}
	public void operate(){
		super.operate();
		System.out.println("Dec 1...");
	}
}
