package com.liuzhenzhen.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuzhenzhen.cms.dao.ChannelDao;
import com.liuzhenzhen.cms.entity.Category;
import com.liuzhenzhen.cms.entity.Channel;
import com.liuzhenzhen.cms.service.ChannelService;
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelDao channelDao;

	public List<Channel> selects() {
		// TODO Auto-generated method stub
		return channelDao.selects();
	}

	public List<Category> sele(Integer channelId) {
		// TODO Auto-generated method stub
		return channelDao.sele(channelId);
	}
}
