package com.frw.service;

import com.frw.bean.UserDo;
import com.frw.dal.IUsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fruwei on 2016/7/20.
 */
@Service
public class UserService {
    @Autowired
    IUsersDao iUsersDao;

    public UserDo addUser(UserDo userDo) {
        iUsersDao.insertOne(userDo);

        return userDo;
    }

}
