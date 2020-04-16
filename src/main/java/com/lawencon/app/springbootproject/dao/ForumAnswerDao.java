package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.ForumAnswer;

public interface ForumAnswerDao {
	abstract List<?> findAll()throws Exception;
	abstract ForumAnswer findById(ForumAnswer forumAnswer)throws Exception;
	abstract ForumAnswer update(ForumAnswer forumAnswer)throws Exception;
	abstract void createForumAnswer(ForumAnswer forumAnswer)throws Exception;
	abstract void delete(ForumAnswer forumAnswer)throws Exception;
}
