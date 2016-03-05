package com.frw.learn.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicRefTest {

	static class User{
		int age;
		String name;
	}

	public static void main(String args[]){

		AtomicReference<User> user=new 	AtomicReference<User>();
		User u1=new User();
		u1.age=1;
		u1.name="1";
		user.set(u1);

		User u2=new User();
		u2.age=2;
		u2.name="2"; 

		User tu1=new User();
		tu1.age=1;
		tu1.name="1"; 
		boolean rel=user.compareAndSet(tu1, u2);

		System.out.println(rel);
		User nu=user.get();
		System.out.println(nu.age+"  "+nu.name);

		
		AtomicReference<Integer> aint=new 	AtomicReference<Integer>();
		aint.set(1);
		System.out.println(aint.compareAndSet(1,11111));
		System.out.println(aint.compareAndSet(11111,11));
		
		
		
	}

}
