package com.liuzhenzhen.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;
import com.liuzhenzhen.cms.entity.Collect;
import com.liuzhenzhen.cms.entity.ContentType;
import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.service.ArticleService;
import com.liuzhenzhen.cms.service.ChannelService;
import com.liuzhenzhen.cms.service.CollectService;

@RequestMapping("my")
@Controller
public class MyController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ChannelService channelService;
	@Autowired
	private CollectService collects;

	@RequestMapping(value = { "", "/", "index" })
	public String index() {

		return "my/index";
	}

	@RequestMapping("articles")
	public String articles(Article article, Model m, @RequestParam(defaultValue = "1") int page, HttpSession session) {
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		PageHelper.startPage(page, 2);
		List<Article> list = articleService.selects(article);
		PageInfo<Article> pa = new PageInfo<Article>(list);
		m.addAttribute("g", list);
		m.addAttribute("info", pa);
		return "my/art";
	}

	@RequestMapping("public")
	public String publich() {
		return "my/publish";
	}

	

	@RequestMapping("articlecha")
	@ResponseBody
	public Article cha(Integer idd) {
		return articleService.cha(idd);
	}

	@RequestMapping("collect")
	public String collect(HttpSession session, Model m) {
		User user = (User) session.getAttribute("user");
		List<Collect> list = collects.select(user.getId());
		m.addAttribute("g", list);
		return "my/collect";
	}

	@RequestMapping("vote")
	public String vote() {
		return "my/vote";
	}

	@RequestMapping("publishVote")
	@ResponseBody
	public int publishVote(String[] options, Article article, HttpSession session) {
		LinkedHashMap<Character, String> map = new LinkedHashMap<Character, String>();
		char x = 'A';
		for (String option : options) {
			map.put(x, option);
			x = (char) (x + 1);
		}
		// 把map转为json
		Gson gson = new Gson();
		String json = gson.toJson(map);
		article.setContent(json);
		article.setContentType(ContentType.VOTE);
		// 封装文件的基本属性
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setStatus(1);// 默认待审核
		article.setHits(0);
		;// 默认点击量为 0
		article.setDeleted(0);// 默认未删除
		article.setCreated(new Date());// 默认发布时间
		article.setUpdated(new Date());// 默认发布时间
		article.setHot(0);// 非热点
		return articleService.insert(article);
	}

}
