package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface LoginService {
	abstract List<Login> findAll() throws Exception;
	abstract List<?> findUsername(String user) throws Exception;
	abstract Boolean validUser(String user, String pass) throws Exception;
	abstract String insertUser(Login login) throws Exception;
	abstract Login update(Login login) throws Exception;
	abstract String deleteById(String id) throws Exception;
	abstract void signUp(SignupRequest signUpRequest)throws Exception;
	Boolean existsByEmail(String email);
}
