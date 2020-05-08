package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Forum;

public interface ForumService {
	
	List<?> findAll() throws Exception;

	List<?> findByIdPertemuan(String idPertemuan) throws Exception;

	Forum findById(String idForum) throws Exception;

	Forum update(Forum forum) throws Exception;

	void createForum(Forum forum) throws Exception;

	void delete(String idForum) throws Exception;
	
	void validate(String idForum) throws Exception;
}
