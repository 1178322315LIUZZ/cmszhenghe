package com.liuzhenzhen.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liuzhenzhen.cms.entity.User;

public interface UserDao {

	List<User> select1(User user);

	int up(@Param("idd")Integer id);

	int u(@Param("idd")Integer id);

	int login(User user);

	User yan(@Param("user")String user);
	

}
