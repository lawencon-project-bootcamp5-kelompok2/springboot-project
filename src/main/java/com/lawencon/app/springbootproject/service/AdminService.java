package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Admin;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface AdminService {
	List<Admin> findAll() throws Exception;
	boolean cekAdmin(String email, String pwd) throws Exception;
	void createAdmin(SignupRequest signUpRequest) throws Exception;
}
