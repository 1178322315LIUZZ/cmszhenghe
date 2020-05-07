package com.liuzhenzhen.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Order;

public interface ArticleDao {

	List<Article> selects(Article article);

	int insert(Article article);

	Article cha(@Param("idd")Integer idd);

	List<Article> select(Article article);

	int upda(@Param("idd")Integer id);

	int upd(@Param("idd")Integer id);

	int pass(@Param("idd")Integer id);

	int pas(@Param("idd")Integer id);

	Article show(Article article);

	List<Article> selectt(Article article);
	@Update("update cms_article set cocount=cocount+1 where id=#{idd}")
	void commentCount(@Param("idd")Integer articleId);

	List<Article> coCount();

	List<Article> selectss(Article article3);

	Article sess(@Param("id")Integer id);
	@Insert("update cms_article set hits=hits+1 where id=#{idd}")
	int click(@Param("idd")Integer id);
	//文章点击量+1
	@Update("update cms_article set hits=#{hits} where id=#{id}")
	void update(Article article);
	@Delete("delete from cms_article where id in (${value})")
	void delete(String id);
	
	//查询节目表
	@Select("select * from cms_programme_order ORDER BY  start desc")
	List<Order> order();
	@Select("select * from cms_programme_order where id=#{id}")
	Order orderid(@Param("id")Integer id);
	//添加明细单
	@Insert("insert into cms_programme_order values(null,#{name},#{state},#{start},#{end},#{addr})")
	void add(Order order);

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
