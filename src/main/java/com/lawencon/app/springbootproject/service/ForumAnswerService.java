package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.ForumAnswer;

public interface ForumAnswerService {
	abstract List<?> findAll()throws Exception;
	abstract ForumAnswer findById(String idAnswer)throws Exception;
	abstract ForumAnswer update(ForumAnswer forumAnswer)throws Exception;
	abstract void createForumAnswer(ForumAnswer forumAnswer)throws Exception;
	abstract void delete(String idAnswer)throws Exception;
}
