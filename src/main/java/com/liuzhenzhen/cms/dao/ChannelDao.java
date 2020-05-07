package com.liuzhenzhen.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;

public interface ChannelDao {

	List<Channel> selects();

	List<Category> sele(@Param("idd")Integer channelId);

}
