package com.liuzhenzhen.cms.service;

import java.util.List;

import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;

public interface ChannelService {

	List<Channel> selects();

	List<Category> sele(Integer channelId);

}
