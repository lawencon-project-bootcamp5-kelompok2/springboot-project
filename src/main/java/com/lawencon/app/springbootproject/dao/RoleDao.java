package com.lawencon.app.springbootproject.dao;

import com.lawencon.app.springbootproject.model.Role;

public interface RoleDao {
	abstract Role findRoleStudent()throws Exception;
	abstract Role findRoleTrainer()throws Exception;
	abstract Role findRoleAdmin()throws Exception;
}
