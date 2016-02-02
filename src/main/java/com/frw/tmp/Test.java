package com.frw.tmp;

import java.util.ArrayList;

class Person{
	public Person(){
		System.out.println("fff");
	}
}

class Student extends Person{
	private String name="ttt";
	public Student(){
		System.out.println("sssss");
	}
}
class A{      
    A()     
    {         
        System.out.println("You call super class non-args constructor!");    
    }  
}  
  
  
 class B extends A  
 {       
    B()       
    {      
        //这里，编译器将自动加上 super();                
        System.out.println("You call subclass constructor!");        
    }                    
    B(String n)         
    {                 
        super();                 
      //  this();//ERROR:编译错误  
        //实际就是调用了B(){...}，而在B(){...}中编译器自动加上了super();这样就相当于两次调用了super();也就是说对父类进行了两次初始化。而在实例化一个对象时，一个构造方法只能调用一次，这说明this和super不能同时存在一个构造方法中。同时因为系统没有在第一行发现this()或super()调用，就会自动加上super(),如果没有将this()和super()放在第一行就会产生矛盾。因为总有一个super()在第二句上。所以该程序不能通过编译！！！    
    }  
 } 
public class Test {

	public static void main(String args[]){
		ArrayList<Integer> s=new ArrayList<Integer>(){
			
		};
	
	}
}
