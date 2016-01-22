package com.frw.dev.redis;

import redis.clients.jedis.Jedis;

public class RedisFirstTest {

	
	public static void main(String args[]){
		Jedis jedis = new Jedis("10.10.195.133");
	      System.out.println("Connection to server sucessfully");
	      //check whether server is running or not
	      System.out.println("Server is running: "+jedis.ping());
	      
	      String name=jedis.get("name");
	      System.out.println("name: "+name);
	}
}
