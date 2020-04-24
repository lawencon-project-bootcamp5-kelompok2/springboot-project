package com.lawencon.app.springbootproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.StudentDao;
import com.lawencon.app.springbootproject.model.Student;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	StudentDao studentDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Student user = new Student();
		try {
			user = studentDao.findByEmail(email);
		} catch (Exception e) {
			new UsernameNotFoundException("User Not Found with email: " + email);
		}
		return UserDetailsImpl.build(user);		
	}

}
