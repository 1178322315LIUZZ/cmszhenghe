package com.liuzhenzhen.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
@RequestMapping("ll")
@Controller
public class EsController {
	@Autowired
	ArticleLight articleLight;
	@RequestMapping("list")
	public String list(Model m) {
		Iterable<Article> findAll = articleLight.findAll();
		m.addAttribute("g", findAll);
		return "list";
	}
}
