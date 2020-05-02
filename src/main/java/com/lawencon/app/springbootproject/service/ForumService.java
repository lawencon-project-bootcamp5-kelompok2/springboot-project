package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Forum;

public interface ForumService {
	abstract List<?> findAll()throws Exception;
	abstract Forum findById(String idForum)throws Exception;
	abstract List<?> findByIdPertemuan(String idPertemuan)throws Exception;
	abstract Forum update(Forum forum)throws Exception;
	abstract void createForum(Forum forum)throws Exception;
	abstract void delete(String idForum)throws Exception;
}
