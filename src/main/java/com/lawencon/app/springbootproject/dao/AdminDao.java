package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Admin;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface AdminDao {
	
	abstract boolean cekAdmin(String email, String pwd); 
	abstract void createAdmin(SignupRequest signUpRequest)throws Exception;
	abstract List<Admin> findAll() throws Exception;
}
