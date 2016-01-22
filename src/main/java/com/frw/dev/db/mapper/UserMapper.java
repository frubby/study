package com.frw.dev.db.mapper;

import java.util.List;

import com.frw.dev.db.bean.UserBean;

public interface UserMapper {

	public List<UserBean> selectAllUser();
}
