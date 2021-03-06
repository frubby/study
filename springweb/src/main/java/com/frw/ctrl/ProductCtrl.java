package com.frw.ctrl;

import com.frw.bean.OrderDo;
import com.frw.bean.ProductDo;
import com.frw.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fruwei on 2016/7/21.
 */
@Controller
@RequestMapping("/product")
public class ProductCtrl {


    @Autowired
    ProductService productService;

    @RequestMapping("/new")
    @ResponseBody
    public String addProduct(ProductDo productDo) {
        productService.addProduct(productDo);
        return "ok";
    }


}
