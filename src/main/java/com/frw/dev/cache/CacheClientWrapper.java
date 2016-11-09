package com.frw.dev.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CacheClientWrapper {

    //5s内一个key被访问3次就认为是热点可以，热点缓存40s
    //ehcache config
    private static final int KEY_DEFAULT_HEAP_SIZE = 10;//默认keycache 内存大小10M
    private static final String KEY_CACHE_NAME = "keyEhcache";//keycache name
    private static final int OBJECT_DEFAULT_HEAP_SIZE = 50;//默认objectcache 内存大小50M
    private static final String OBJECT_CACHE_NAME = "ObjectEhcache";//objectcache name
    private static final int KEY_TIMEOUT = 5;//keycache缓存失效时间5s
    private static final int OBJECT_TIMEOUT = 10;//object缓存失效时间40s
    private static final CacheManager manager;
    private static final Cache keyCache;
    private static final Cache objectCache;
    private final int keyHitCount = 3;//key访问计数

    private static final String EHCACHE_ENABLE_NAME = "product.client.ehcache.enable";//ehcache开关

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheClientWrapper.class);


    static {
        //init ehcache
        manager = CacheManager.create();
        keyCache = new Cache(
                new CacheConfiguration()
                        .name(KEY_CACHE_NAME + UUID.randomUUID().toString())
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .maxBytesLocalHeap(KEY_DEFAULT_HEAP_SIZE, MemoryUnit.MEGABYTES)
                        .timeToLiveSeconds(KEY_TIMEOUT)
                        .timeToIdleSeconds(KEY_TIMEOUT));
        manager.addCache(keyCache);

        objectCache = new Cache(
                new CacheConfiguration()
                        .name(OBJECT_CACHE_NAME + UUID.randomUUID().toString())
                        .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU)
                        .eternal(false)
                        .maxBytesLocalHeap(OBJECT_DEFAULT_HEAP_SIZE, MemoryUnit.MEGABYTES)
                        .timeToLiveSeconds(OBJECT_TIMEOUT)
                        .timeToIdleSeconds(OBJECT_TIMEOUT));
        manager.addCache(objectCache);
        objectCache.setStatisticsEnabled(true);
        moniorEhcacheHIT();
    }


    private static void moniorEhcacheHIT() {
//        new Thread("moniorEhcacheHIT") {
//            @Override
//            public void run() {
//                new Timer("moniorEhcacheHIT-Timer", true).schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        LOGGER.info("ehcache hit={}", objectCache.getStatistics().toString());
//                    }
//                }, 0, 2000L);
//            }
//        }.start();
    }


    public static void main(String args[]) {

        LOGGER.info("################## ehcache hit={}", objectCache.getStatistics().toString());

        keyCache.put(new Element("test", 1));
        keyCache.put(new Element("test1", 1));
        keyCache.put(new Element("test2", 1));
        keyCache.put(new Element("test3", 1));
        keyCache.put(new Element("test4", 1));
        LOGGER.info("################# ehcache hit={}",keyCache.getKeysWithExpiryCheck().size());

        Element value = keyCache.get("test");

        LOGGER.info("################## ehcache hit={}   {}", keyCache.getStatistics().toString(),value.getObjectValue());

        try {
            Thread.sleep(14000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.info("################# ehcache hit={}", keyCache.getStatistics().toString());
        LOGGER.info("################# ehcache hit={}",keyCache.getKeysWithExpiryCheck().size());

        value = keyCache.get("test");
        if(value!=null)
            value.getObjectValue();
        LOGGER.info("################# ehcache hit={}", keyCache.getKeysWithExpiryCheck().size());
        LOGGER.info("################# ehcache hit={}", keyCache.getStatistics().toString());

    }
}
