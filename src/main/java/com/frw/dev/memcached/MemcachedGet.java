package com.frw.dev.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class MemcachedGet {
	public static void main(String[] args) {
		int portNum=12000;
		MemcachedClient c;
		try {
			c = new MemcachedClient(
					new InetSocketAddress("10.10.195.133", portNum));

			Object myObject=c.get("someKey");
			System.out.println(myObject.toString());
			c.shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
