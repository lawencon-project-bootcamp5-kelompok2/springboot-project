package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface LoginService {
	
	List<Login> findAll() throws Exception;

	List<?> findUsername(String user) throws Exception;

	Login update(Login login) throws Exception;

	Boolean existsByEmail(String email);

	String insertUser(Login login) throws Exception;

	String deleteById(String id) throws Exception;

	void signUp(SignupRequest signUpRequest) throws Exception;

}
