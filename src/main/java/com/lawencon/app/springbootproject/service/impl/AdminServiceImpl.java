package com.lawencon.app.springbootproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.AdminDao;
import com.lawencon.app.springbootproject.service.AdminService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Override
	public boolean cekAdmin(String email, String pwd) {
		return adminDao.cekAdmin(email, pwd);
	}
	
	
}
