package com.frw.dev.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedFirst {

	public static void main(String[] args) {
		int portNum=12000;
		MemcachedClient c;
		try {
			c = new MemcachedClient(
					new InetSocketAddress("10.10.195.133", portNum));
			// Store a value (async) for one hour
			c.set("someKey", 3600, "eeeee");
			// Retrieve a value (synchronously).
			Object myObject=c.get("someKey");
			System.out.println(myObject.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
