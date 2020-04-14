package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Login;

public interface LoginDao {
	abstract List<Login> findAll() throws Exception;
	abstract List<?> findByUsername(String user) throws Exception;
	abstract Login validUser(String user, String pass) throws Exception;
	abstract String insertUser(Login login) throws Exception;
	abstract Login update(int id, String user, String pass, String role) throws Exception;
	abstract String deleteById(int id) throws Exception;
}
