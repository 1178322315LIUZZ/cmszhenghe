package com.liuzhenzhen.cms.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.liuzhenzhen.cms.entity.Article;

public interface ArticleLight extends ElasticsearchRepository<Article, Integer>{
	Article findByContent(String content);
}
