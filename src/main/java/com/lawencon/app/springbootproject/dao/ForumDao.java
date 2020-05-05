package com.lawencon.app.springbootproject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.model.Forum;

@Repository
public interface ForumDao {
	
	List<?> findAll() throws Exception;

	List<?> findByIdPertemuan(String idPertemuan) throws Exception;

	Forum findById(String idForum) throws Exception;

	Forum update(Forum forum) throws Exception;

	void createForum(Forum forum) throws Exception;

	void delete(String idForum) throws Exception;
}
