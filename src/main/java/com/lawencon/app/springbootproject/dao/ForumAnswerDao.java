package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.ForumAnswer;

public interface ForumAnswerDao {
	
	List<?> findAll() throws Exception;

	ForumAnswer findById(String idAnswer) throws Exception;

	ForumAnswer update(ForumAnswer forumAnswer) throws Exception;

	void createForumAnswer(ForumAnswer forumAnswer) throws Exception;

	void delete(String idAnswer) throws Exception;
}
