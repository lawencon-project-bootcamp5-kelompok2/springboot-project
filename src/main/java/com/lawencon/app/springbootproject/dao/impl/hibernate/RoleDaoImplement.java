package com.lawencon.app.springbootproject.dao.impl.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.RoleDao;
import com.lawencon.app.springbootproject.model.ERole;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.repository.RoleRepository;

@Repository
public class RoleDaoImplement implements RoleDao{

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findRoleStudent() throws Exception {
		return roleRepository.findByName(ERole.ROLE_STUDENT).
		orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	}

	@Override
	public Role findRoleTrainer() throws Exception {
		return roleRepository.findByName(ERole.ROLE_TRAINER).
		orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	}

	@Override
	public Role findRoleAdmin() throws Exception {
		return roleRepository.findByName(ERole.ROLE_ADMIN).
		orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	}

}
