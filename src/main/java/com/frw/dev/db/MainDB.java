package com.frw.dev.db;

import com.frw.dev.db.bean.UserBean;
import com.frw.dev.db.mapper.UserMapper;
import com.google.gson.Gson;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainDB {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String resource = "db/mybatis_config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory  sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			
			SqlSession session=sqlSessionFactory.openSession();
			UserMapper userMapper=session.getMapper(UserMapper.class);
			List<UserBean> users=userMapper.selectAllUser();
			
			
			
			Gson gson=new Gson();
			String rel=gson.toJson(users);
			System.out.println(rel);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
