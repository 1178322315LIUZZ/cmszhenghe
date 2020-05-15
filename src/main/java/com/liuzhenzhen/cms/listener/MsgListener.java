package com.liuzhenzhen.cms.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.liuzhenzhen.cms.dao.ArticleDao;
import com.liuzhenzhen.cms.dao.ArticleLight;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.service.ArticleService;

public class MsgListener implements MessageListener<String,String>{
	@Autowired
	private ArticleService articleMapper;
	@Autowired
	ArticleDao dao;
	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	ArticleLight light;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.out.println("接受到消息");
		String value = data.value();
//		//2.将json转成一个对象
//		Article article = JSON.parseObject(value, Article.class);
//		//3.将这个article对象,保存到mysql数据库(cms_article表当中)
//		articleMapper.insert(article);
//		System.err.println("保存到mysql成功!!!!!!!!!!!");
		if(value.startsWith("art")) {
			String[] split = value.split("=");
			Article article2 = JSON.parseObject(split[1], Article.class);
			//保存数据到数据库
			dao.insert(article2);
			//保存数据到es库
			light.save(article2);
			System.err.println("通过kafka添加数据成功");
			System.err.println("保存es库成功");
		}
	}
}
