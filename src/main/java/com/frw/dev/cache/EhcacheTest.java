package com.frw.dev.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.CacheManagerBuilder;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.CacheConfigurationBuilder;

public class EhcacheTest {

	
	public static void main(String args[]){
		
		CacheConfiguration<Integer, String> preConfig=CacheConfigurationBuilder.newCacheConfigurationBuilder().buildConfig(Integer.class, String.class);
		CacheManager cacheManager=CacheManagerBuilder.newCacheManagerBuilder().build(false);//.withCache("preConfig", preConfig).build(false);
		cacheManager.init();
		
//		Cache<Long, String> preConfigured =
//			    cacheManager.getCache("preConfigured", Long.class, String.class);
		Cache<Integer ,String> mycache=cacheManager.createCache("mycache", preConfig);
		mycache.put(1, "frw");
		mycache.put(1, "frw");
		mycache.put(1, "test");
		mycache.put(1, "fafa");
		
		
		System.out.println("cache : "+mycache.get(1));
		
	}




}
