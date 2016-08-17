package com.frw.mybatis.dao;

import com.frw.mybatis.bean.User;

import java.util.List;

/**
 * Created by fruwei on 2016/8/17.
 */
public interface UserDao {
    public void insert(User user);

    public User findUserById(int userId);

    public List<User> findAllUsers();

}
