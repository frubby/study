package com.frw.test.initTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by fruwei on 2016/10/31.
 */
@Component
public class DependBeanOne {

    Logger log= LoggerFactory.getLogger("test");
    public DependBeanOne(){
        log.info("###### DependBeanOne");
    }

}
