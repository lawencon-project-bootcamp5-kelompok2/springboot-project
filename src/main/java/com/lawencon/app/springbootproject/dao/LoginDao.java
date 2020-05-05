package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Login;

public interface LoginDao {
	
	List<Login> findAll() throws Exception;

	List<?> findByUsername(String user) throws Exception;

	Login findByEmail(String email);

	Login update(Login login) throws Exception;

	String insertUser(Login login) throws Exception;

	String deleteById(String id) throws Exception;

	String deleteByEmail(String email) throws Exception;

	Boolean existsByEmail(String email);
}
