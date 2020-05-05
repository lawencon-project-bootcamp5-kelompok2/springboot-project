package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.ForumAnswer;

public interface ForumAnswerService {
	
	List<?> findAll() throws Exception;

	ForumAnswer findById(String idAnswer) throws Exception;

	ForumAnswer update(ForumAnswer forumAnswer) throws Exception;

	void createForumAnswer(ForumAnswer forumAnswer) throws Exception;

	void delete(String idAnswer) throws Exception;
}
