package com.liuzhenzhen.cms.service;

import java.util.List;

import com.liuzhenzhen.cms.entity.Vote;

public interface VoteService {

	Vote select(Vote vote);

	int insert(Vote vote);

	List<Vote> selects(Integer id);

}
