package com.liuzhenzhen.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.filter.AutoLoad;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liuzhenzhen.cms.dao.ArticleDao;
import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Order;
import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.service.ArticleService;
import com.liuzhenzhen.cms.service.UserService;
@RequestMapping("admin")
@Controller
public class AdminController {
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleLight articleLight;
	@RequestMapping(value = {"","/","admin"})
	public String admin() {
		return "admin/index";
	}
	
	@RequestMapping("articles")
	public String articles(Article article,Model m,@RequestParam(defaultValue = "1")int page) {
		PageHelper.startPage(page, 2);
		List<Article> list = articleService.select(article);
		PageInfo<Article> pa=new PageInfo<Article>(list);
		m.addAttribute("g", list);
		m.addAttribute("info", pa);
		return "admin/articles";
	}
	
	@RequestMapping("user")
	public String users(User user, Model m,@RequestParam(defaultValue = "1")int page) {
		PageHelper.startPage(page, 2);
		List<User> list = userService.select1(user);
		PageInfo<User> pa=new PageInfo<User>(list);
		m.addAttribute("g", list);
		m.addAttribute("info", pa);
		m.addAttribute("us", user);
		return "admin/user";
	}
	
	@RequestMapping("danshan")
	public String danshan(String id) {
		articleDao.delete(id);
		return "redirect:admin";
	}
	//添加节目表
	@RequestMapping("orderadd")
	public String add(Order order,Model m) {
		m.addAttribute("o", order);
		return "orderadd";
	}
}
