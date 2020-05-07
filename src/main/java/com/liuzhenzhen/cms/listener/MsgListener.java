package com.liuzhenzhen.cms.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.service.ArticleService;

public class MsgListener implements MessageListener<String,String>{
	@Autowired
	private ArticleService articleMapper;
	@Override
	public void onMessage(ConsumerRecord<String, String> data) {
		System.out.println("接受到消息");
		String value = data.value();
		//2.将json转成一个对象
		Article article = JSON.parseObject(value, Article.class);
		//3.将这个article对象,保存到mysql数据库(cms_article表当中)
		articleMapper.insert(article);
		System.err.println("保存到mysql成功!!!!!!!!!!!");
	}
}
