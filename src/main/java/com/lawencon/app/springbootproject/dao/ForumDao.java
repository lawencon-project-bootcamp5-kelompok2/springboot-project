package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.model.Forum;

@Repository
public interface ForumDao{
	abstract List<?> findAll()throws Exception;
	abstract Forum findById(Forum forum)throws Exception;
	abstract void createForum(Forum forum)throws Exception;
	abstract Forum update(Forum forum)throws Exception;
	abstract void delete (Forum forum)throws Exception;
}