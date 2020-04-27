package com.lawencon.app.springbootproject.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.RoleDao;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role findRoleStudent(){
		try {
			return roleDao.findRoleStudent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findRoleTrainer() {
		try {
			return roleDao.findRoleTrainer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role findRoleAdmin(){
		try {
			return roleDao.findRoleAdmin();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
