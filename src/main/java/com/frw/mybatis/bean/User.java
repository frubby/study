package com.frw.mybatis.bean;

import lombok.Data;

/**
 * Created by fruwei on 2016/8/17.
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private int age;
    private int deleteFlag;
}
