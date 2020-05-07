package com.liuzhenzhen.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liuzhenzhen.cms.entity.Collect;


public interface CollectMapper {

	int insert(Collect collect);
	
	int delete(Integer id);

	List<Collect> selects(Integer userId);
	
	Collect selectByTitleAndUserId(@Param("title")String title,@Param("userId")Integer userId);
	@Select("select * from cms_collect where user_id=#{idd}")
	List<Collect> select(@Param("idd")Integer id);
	

}
