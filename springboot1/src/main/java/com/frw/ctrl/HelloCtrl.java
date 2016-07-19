package com.frw.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fruwei on 2016/7/19.
 */
@Controller
public class HelloCtrl {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
