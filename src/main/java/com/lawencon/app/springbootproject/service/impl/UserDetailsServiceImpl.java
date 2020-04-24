package com.lawencon.app.springbootproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.model.Login;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	LoginDao loginDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Login user = new Login();
		try {
			user = loginDao.findByEmail(email);
		} catch (Exception e) {
			new UsernameNotFoundException("User Not Found with email: " + email);
		}
		return UserDetailsImpl.build(user);		
	}

}
