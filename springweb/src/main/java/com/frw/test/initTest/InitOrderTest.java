package com.frw.test.initTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by fruwei on 2016/10/31.
 */
@Component
public class InitOrderTest {

    Logger log= LoggerFactory.getLogger("test");


    @Autowired
    private DependBeanOne dependBeanOne;

    public InitOrderTest(){
        log.info("#####init");
    }








}
