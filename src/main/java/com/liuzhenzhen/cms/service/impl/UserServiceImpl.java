package com.liuzhenzhen.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuzhenzhen.cms.dao.UserDao;
import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.service.UserService;
import com.liuzhenzhen.cms.util.CMSException;
import com.liuzhenzhen.cms.util.Md5Util;
import com.zhenzhen.common.utils.StringUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public List<User> select1(User user) {
		// TODO Auto-generated method stub
		return userDao.select1(user);
	}

	public int up(Integer id) {
		// TODO Auto-generated method stub
		return userDao.up(id);
	}

	public int u(Integer id) {
		// TODO Auto-generated method stub
		return userDao.u(id);
	}

	public int login(User user) {

		if (!StringUtil.hasText(user.getUsername())) {
			throw new CMSException("用户名不能为空");
		}
		if (!(user.getUsername().length() >= 2 && user.getUsername().length() <= 10)) {
			throw new CMSException("用户名的长度在2-10之间");
		}
		User yan = this.yan(user.getUsername());
		if (yan != null) {
			throw new CMSException("用户已经存在");
		}
		if (!StringUtil.hasText(user.getPassword())) {
			throw new CMSException("密码不能为空");
		}
		if (!(user.getPassword().length() >= 6 && user.getPassword().length() <= 10)) {
			throw new CMSException("密码长度在6-10之间");
		}
		user.setPassword(Md5Util.encode(user.getPassword()));
		// 初始用户的注册信息----
		Date date = new Date();
		user.setCreated(date);// 注册时间
		user.setUpdated(date);
		user.setNickname(user.getUsername());// 默认用户昵称为用户名称
		user.setLocked(0);// 默认用户状态是可用的
		user.setBirthday(date);
		return userDao.login(user);
	}

	public User yan(String user) {
		// TODO Auto-generated method stub
		return userDao.yan(user);
	}

	public User denglu(User user) {
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		// 2 检查用户名是否存在
		User u = this.yan(user.getUsername());
		if (null == u) {
			throw new CMSException("该用户不存在");
		}
		if (u.getLocked() == 1) {
			throw new CMSException("该用户已禁用");
		}
		// 3 比较密码是否一致 //数据库存储的是 加密后的密码
		// 对登录的密码再进行加密 再和数据库的密码进行比较
		if (!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
			throw new CMSException("密码不正确，请重新输入");
		return u;
	}

	public User admindenglu(User user) {
		if (!StringUtil.hasText(user.getUsername()))
			throw new CMSException("用户名不能为空");
		// 2 检查用户名是否存在
		User u = this.yan(user.getUsername());
		if (null == u) {
			throw new CMSException("该用户不存在");
		}
		if (u.getRole() != 1) {
			throw new CMSException("该用户不是管理员");
		}
		// 3 比较密码是否一致 //数据库存储的是 加密后的密码
		// 对登录的密码再进行加密 再和数据库的密码进行比较
		if (!Md5Util.encode(user.getPassword()).equals(u.getPassword()))
			throw new CMSException("密码不正确,请重新输入");
		return u;
	}
}
