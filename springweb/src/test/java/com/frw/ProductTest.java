package com.frw;

import com.frw.bean.ProductDo;
import com.frw.bean.UserDo;
import com.frw.ctrl.ProductCtrl;
import com.frw.ctrl.UserTestCtl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by fruwei on 2016/7/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:spring/spring*.xml"})
public class ProductTest {

    @Autowired
    ProductCtrl productCtrl;

    @Test
    public void test() {
        ProductDo productDo = new ProductDo();
        productDo.setName("frw");
        productDo.setPrice(100.0);
        productDo.setNum(100);
        productDo.setSku(100001);


        productCtrl.addProduct(productDo);
    }

}



