package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.ForumDao;
import com.lawencon.app.springbootproject.model.Forum;
import com.lawencon.app.springbootproject.service.ForumService;

@Service
@Transactional
public class ForumServiceImpl implements ForumService{

	@Autowired
	private ForumDao forumDao;

	@Override
	public List<?> findAll() throws Exception {
		return forumDao.findAll();
	}

	@Override
	public Forum findById(String idForum) throws Exception {
		return forumDao.findById(idForum);
	}

	@Override
	public Forum update(Forum forum) throws Exception {
		return forumDao.update(forum);
	}

	@Override
	public void createForum(Forum forum) throws Exception {
		forumDao.createForum(forum);
	}

	@Override
	public void delete(String idForum) throws Exception {
		forumDao.delete(idForum);
	}

	@Override
	public Forum findByIdSubcourse(String idSubcourse) throws Exception {
		return forumDao.findByIdSubcourse(idSubcourse);
	}
}
