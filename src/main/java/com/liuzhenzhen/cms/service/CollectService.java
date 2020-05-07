package com.liuzhenzhen.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuzhenzhen.cms.entity.Collect;


public interface CollectService {

	
	
	int insert(Collect collect);
	
	int delete(Integer id);
	
	List<Collect> selects(Integer userId);
	
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);

	List<Collect> select(Integer id);
	
}
