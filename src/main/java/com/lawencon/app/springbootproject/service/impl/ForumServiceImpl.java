package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

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
		validate(forum.getIdForum());
		return forumDao.update(forum);
	}

	@Override
	public void createForum(Forum forum) throws Exception {
		forumDao.createForum(forum);
	}

	@Override
	public void delete(String idForum) throws Exception {
		validate(idForum);
		forumDao.delete(idForum);
	}

	@Override
	public List<?> findByIdPertemuan(String idPertemuan) throws Exception {
		return forumDao.findByIdPertemuan(idPertemuan);
	}

	@Override
	public void validate(String idForum) throws Exception {
		try {
			forumDao.findById(idForum);
		} catch (NoResultException e) {
			throw new Exception("Wrong id");
		}
		
	}
}
