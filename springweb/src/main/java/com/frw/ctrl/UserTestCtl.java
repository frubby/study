package com.frw.ctrl;

import com.frw.bean.UserDo;
import com.frw.service.UserService;
import com.frw.util.DateTimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fruwei on 2016/7/20.
 */
@Controller
@RequestMapping("/user")
public class UserTestCtl {


    @Autowired
    UserService userService;

    @RequestMapping("/testAdd")
    @ResponseBody
    public UserDo testInsert(@RequestBody UserDo user) {
        return userService.addUser(user);
    }


    @RequestMapping("/test")
    @ResponseBody
    public String testInsert() {
        return DateTimeHelper.getNowInStr();
    }

}
