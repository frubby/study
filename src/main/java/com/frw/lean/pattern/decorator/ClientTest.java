package com.frw.lean.pattern.decorator;

public class ClientTest {

	public static void main(String[] args) {
		

		
		Component real=new ConcreteComponent();
//		Decorator dec=new Decorator(real);
		Component real_dec1=new RealDec1(real);
		Component real_dec2=new RealDec2(real_dec1);
		real_dec2.operate();

	}

}
