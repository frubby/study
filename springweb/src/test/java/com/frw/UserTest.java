package com.frw;

import com.frw.bean.UserDo;
import com.frw.ctrl.UserTestCtl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fruwei on 2016/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring*.xml"})
public class UserTest {


    @Autowired
    UserTestCtl userTestCtl;
//    @Test
    public  void test(){
        UserDo userDo=new UserDo();
        userDo.setUid(1);
        userDo.setName("frw");
        userDo.setPwd("frw");


        userTestCtl.testInsert(userDo);
    }

}
