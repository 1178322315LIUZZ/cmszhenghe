package com.liuzhenzhen.cms.service;

import java.util.List;

import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Order;

public interface ArticleService {
	
	List<Article> selects(Article article);
	
	int insert(Article article);

	Article cha(Integer idd);

	List<Article> select(Article article);

	int upda(Integer id);

	int upd(Integer id);

	int pass(Integer id);

	int pas(Integer id);

	Article show(Article article);

	List<Article> selectt(Article article);

	void commentCount(Integer articleId);

	List<Article> coCount();

	List<Article> selectss(Article article3);

	Article sess(Integer id);

	int click(Integer id);
	//文章点击量+1
	void update(Article article);
	
	
	//节目单查询
	List<Order> order();
	//根据id查节目
	Order orderid(Integer id);
	//添加明细单
	void add(Order order);

}
