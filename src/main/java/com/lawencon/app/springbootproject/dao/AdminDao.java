package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Admin;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface AdminDao {
	boolean cekAdmin(String email, String pwd);
	void createAdmin(SignupRequest signUpRequest) throws Exception;
	List<Admin> findAll() throws Exception;
}
