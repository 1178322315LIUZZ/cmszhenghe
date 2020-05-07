package com.liuzhenzhen.cms.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;
import com.liuzhenzhen.cms.entity.Comment;
import com.liuzhenzhen.cms.entity.User;
import com.liuzhenzhen.cms.service.ArticleService;
import com.liuzhenzhen.cms.service.ChannelService;
import com.liuzhenzhen.cms.service.CommentService;
import com.liuzhenzhen.cms.service.UserService;
@Controller
@RequestMapping("chaxun")
public class ChaController {
	@Autowired
	ChannelService channelService;
	@Autowired
	ArticleService articleService;
	@Autowired
	ArticleLight articleLight;
	@Autowired
	UserService userService;
	@Autowired
	private CommentService commentService;
	@GetMapping("chan")
	@ResponseBody
	public List<Channel> chann() {
		return channelService.selects();
	}

	@GetMapping("cate")
	@ResponseBody
	public List<Category> cate(Integer channelId) {
		return channelService.sele(channelId);
	}
	@ResponseBody
	@PostMapping("fabu")
	public int publish(@RequestParam("file2") MultipartFile file, Article article, HttpSession session) {

		if (!file.isEmpty()) {
			String upload = "d:/pic/"; // 文件路径
			// 获取文件名称
			String filename = file.getOriginalFilename();
			// 防止文件重名。改文件名称
			String newFilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
			File f = new File(upload, newFilename);
			try {
				file.transferTo(f);
			} catch (IllegalStateException e) {
			} catch (IOException e) {
				e.printStackTrace();
			}
			article.setPicture(newFilename);// 封装上传的文件名称
		}
		// 封装文件的基本属性
		User user = (User) session.getAttribute("user");
		article.setUserId(user.getId());
		article.setStatus(0);// 默认待审核
		article.setHits(0);
		;// 默认点击量为 0
		article.setDeleted(0);// 默认未删除
		article.setCreated(new Date());// 默认发布时间
		article.setUpdated(new Date());// 默认发布时间
		// article.setContentType(0);//发布的文章类型
		article.setHot(0);// 非热点
		return articleService.insert(article);
	}
	@RequestMapping("articlecha")
	@ResponseBody
	public Article cha(Integer idd) {
		Article article = articleService.cha(idd);
		return article;
	}
	@RequestMapping("pass")
	@ResponseBody
	public int pass(Integer id) {
		Article article = articleService.cha(id);
		articleLight.save(article);
		return articleService.pass(id);
	}
	@RequestMapping("pas")
	@ResponseBody
	public int pas(Integer id) {
		return articleService.pas(id);
	}
	@RequestMapping("upda")
	@ResponseBody
	public int upda(Integer id) {
		return articleService.upda(id);
	}
	@RequestMapping("upd")
	@ResponseBody
	public int upd(Integer id) {
		return articleService.upd(id);
	}
	@RequestMapping("up")
	@ResponseBody
	public int up(Integer id) {
		return userService.up(id);
	}
	@RequestMapping("u")
	@ResponseBody
	public int u(Integer id) {
		return userService.u(id);
	}
	@RequestMapping("in")
	@ResponseBody
	public int insert(Comment comment,Integer articleId,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user==null) {
			return 0;
		}else {
			comment.setUserId(user.getId());
			comment.setArticleId(articleId);
			comment.setCreated(new Date());
			articleService.commentCount(articleId);
			return commentService.insert(comment);
		}
	}
}
