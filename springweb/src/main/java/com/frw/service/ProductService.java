package com.frw.service;

import com.frw.bean.ProductDo;
import com.frw.dal.IProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fruwei on 2016/7/21.
 */
@Service
public class ProductService {
    @Autowired
    IProductDao productDao;


    public void addProduct(ProductDo productDo) {
        productDao.insertOne(productDo);
    }
}
