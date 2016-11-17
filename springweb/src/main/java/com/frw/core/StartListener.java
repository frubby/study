package com.frw.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by fruwei on 2016/10/25.
 */
@Service
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {


    Logger log= LoggerFactory.getLogger("test");
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent()==null) {
            System.out.println("##################### start in StartListener...");
            log.info("###########################  start in StartListener...");
        }

    }
}
