package com.lawencon.app.springbootproject.dao;

import com.lawencon.app.springbootproject.model.Role;

public interface RoleDao {
	Role findRoleStudent() throws Exception;
	Role findRoleTrainer() throws Exception;
	Role findRoleAdmin() throws Exception;
}
