package com.frw.ctrl;

import com.frw.bean.OrderDo;
import com.frw.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fruwei on 2016/7/21.
 */
@Controller
@RequestMapping("/order")
public class OrdersCtrl {
    @Autowired
    OrderService orderService;

    @RequestMapping("/create")
    @ResponseBody
    public String addOrder(OrderDo orderDo) {
        orderService.addOrder(orderDo);
        return "ok";
    }
}
