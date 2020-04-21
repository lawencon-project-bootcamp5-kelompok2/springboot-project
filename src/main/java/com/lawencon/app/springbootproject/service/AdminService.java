package com.lawencon.app.springbootproject.service;

public interface AdminService {
	abstract boolean cekAdmin(String email, String pwd) throws Exception; 
}
