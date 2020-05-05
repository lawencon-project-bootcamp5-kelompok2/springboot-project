package com.lawencon.app.springbootproject.service;

import com.lawencon.app.springbootproject.model.Role;

public interface RoleService {
	Role findRoleStudent();
	Role findRoleTrainer();
	Role findRoleAdmin();
}
