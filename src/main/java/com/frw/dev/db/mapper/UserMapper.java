package com.frw.dev.db.mapper;

import com.frw.dev.db.bean.UserBean;

import java.util.List;

public interface UserMapper {

	public List<UserBean> selectAllUser();
}
