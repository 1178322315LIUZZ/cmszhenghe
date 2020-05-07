package com.liuzhenzhen.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liuzhenzhen.cms.dao.VoteDao;
import com.liuzhenzhen.cms.entity.Vote;
import com.liuzhenzhen.cms.service.VoteService;
@Service
public class VoteServiceImpl implements VoteService {
	@Autowired
	private VoteDao voteDao;

	public Vote select(Vote vote) {
		// TODO Auto-generated method stub
		return voteDao.select(vote);
	}

	public int insert(Vote vote) {
		// TODO Auto-generated method stub
		return voteDao.insert(vote);
	}

	public List<Vote> selects(Integer id) {
		// TODO Auto-generated method stub
		return voteDao.selects(id);
	}
}
