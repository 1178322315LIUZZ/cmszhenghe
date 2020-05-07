package com.liuzhenzhen.cms.service.impl;

import java.awt.color.CMMException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuzhenzhen.cms.dao.CollectMapper;
import com.liuzhenzhen.cms.entity.Collect;
import com.liuzhenzhen.cms.service.CollectService;
import com.zhenzhen.common.utils.StringUtil;


@Service
public class CollectServiceImpl  implements CollectService{
	@Autowired
	CollectMapper collectMapper;

	public int insert(Collect collect) {
		if(!StringUtil.isHttpUrl(collect.getUrl()))
			throw new CMMException("地址不合法");
		return collectMapper.insert(collect);
	}

	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.delete(id);
	}

	public List<Collect> selects(Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selects(userId);
	}

	public Collect selectByTitleAndUserId(String title, Integer userId) {
		// TODO Auto-generated method stub
		return collectMapper.selectByTitleAndUserId(title, userId);
	}

	public List<Collect> select(Integer id) {
		// TODO Auto-generated method stub
		return collectMapper.select(id);
	}

}
