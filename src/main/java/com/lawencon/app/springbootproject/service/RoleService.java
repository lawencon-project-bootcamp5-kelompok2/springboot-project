package com.lawencon.app.springbootproject.service;

import com.lawencon.app.springbootproject.model.Role;

public interface RoleService {
	abstract Role findRoleStudent();
	abstract Role findRoleTrainer();
	abstract Role findRoleAdmin();
}
