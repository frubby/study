package com.frw.service;

import com.frw.bean.OrderDo;
import com.frw.dal.IOrdersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fruwei on 2016/7/21.
 */
@Service
public class OrderService {
    @Autowired
    IOrdersDao ordersDao;

    public void addOrder(OrderDo orderDo){
        ordersDao.insertOne(orderDo);
    }
}
