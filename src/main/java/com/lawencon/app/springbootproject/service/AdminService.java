package com.lawencon.app.springbootproject.service;

import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface AdminService {
	abstract boolean cekAdmin(String email, String pwd) throws Exception; 
	abstract void createAdmin(SignupRequest signUpRequest)throws Exception;
}
