package com.frw.dal;

import com.frw.bean.UserDo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by fruwei on 2016/7/20.
 */
public interface IUsersDao {
    public int insertOne(UserDo user);

    public int selectOne(int uid);

}
