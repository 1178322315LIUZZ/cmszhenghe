package com.liuzhenzhen.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuzhenzhen.cms.dao.ArticleDao;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Order;
import com.liuzhenzhen.cms.service.ArticleService;
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public List<Article> selects(Article article) {
		// TODO Auto-generated method stub
		return articleDao.selects(article);
	}

	public int insert(Article article) {
		// TODO Auto-generated method stub
		return articleDao.insert(article);
	}

	public Article cha(Integer idd) {
		// TODO Auto-generated method stub
		return articleDao.cha(idd);
	}

	public List<Article> select(Article article) {
		// TODO Auto-generated method stub
		return articleDao.select(article);
	}

	public int upda(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.upda(id);
	}

	public int upd(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.upd(id);
	}

	public int pass(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.pass(id);
	}

	public int pas(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.pas(id);
	}

	public Article show(Article article) {
		// TODO Auto-generated method stub
		return articleDao.show(article);
	}

	public List<Article> selectt(Article article) {
		// TODO Auto-generated method stub
		return articleDao.selectt(article);
	}

	public void commentCount(Integer articleId) {
		articleDao.commentCount(articleId);
	}

	public List<Article> coCount() {
		// TODO Auto-generated method stub
		return articleDao.coCount();
	}

	public List<Article> selectss(Article article3) {
		// TODO Auto-generated method stub
		return articleDao.selectss(article3);
	}

	public Article sess(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.sess(id);
	}
	//添加点击量
	public int click(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.click(id);
	}
	//文章点击量+1
	@Override
	public void update(Article article) {
		articleDao.update(article);
	}
	
	
	//节目单查询
	@Override
	public List<Order> order() {
		// TODO Auto-generated method stub
		return articleDao.order();
	}
	//根据id查节目
	@Override
	public Order orderid(Integer id) {
		// TODO Auto-generated method stub
		return articleDao.orderid(id);
	}
	//添加明细单
	@Override
	public void add(Order order) {
		articleDao.add(order);
	}

	
	
}
