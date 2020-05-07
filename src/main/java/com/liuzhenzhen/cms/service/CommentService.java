package com.liuzhenzhen.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.liuzhenzhen.cms.entity.Article;
import com.liuzhenzhen.cms.entity.Comment;

public interface CommentService {

	

	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加评论
	 * @param comment
	 * @return
	 * @return: int
	 */
	int insert(Comment comment);

	List<Comment> select(Article article);
}
	
