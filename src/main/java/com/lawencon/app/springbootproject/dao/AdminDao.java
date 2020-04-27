package com.lawencon.app.springbootproject.dao;

import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface AdminDao {
	
	abstract boolean cekAdmin(String email, String pwd); 
	abstract void createAdmin(SignupRequest signUpRequest)throws Exception;
}
