package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.ForumAnswerDao;
import com.lawencon.app.springbootproject.model.ForumAnswer;
import com.lawencon.app.springbootproject.service.ForumAnswerService;

@Service
@Transactional
public class ForumAnswerServiceImpl implements ForumAnswerService{

	@Autowired
	private ForumAnswerDao forumAnswerDao;

	@Override
	public List<?> findAll() throws Exception {
		return forumAnswerDao.findAll();
	}

	@Override
	public ForumAnswer findById(String idAnswer) throws Exception {
		return forumAnswerDao.findById(idAnswer);
	}

	@Override
	public ForumAnswer update(ForumAnswer forumAnswer) throws Exception {
		validate(forumAnswer.getIdAnswer());
		return forumAnswerDao.update(forumAnswer);
	}

	@Override
	public void createForumAnswer(ForumAnswer forumAnswer) throws Exception {
		forumAnswerDao.createForumAnswer(forumAnswer);
	}

	@Override
	public void delete(String idAnswer) throws Exception {
		validate(idAnswer);
		forumAnswerDao.delete(idAnswer);
	}

	@Override
	public void validate(String idAnswer) throws Exception {
		try {
			forumAnswerDao.findById(idAnswer);
		} catch (NoResultException e) {
			throw new Exception("Wrong id");
		}
	}
}
