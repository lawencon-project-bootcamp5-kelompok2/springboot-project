package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.service.LoginService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	@Qualifier("login_repo_hibernate")
	private LoginDao loginDao;
	
	@Override
	public List<Login> findAll() throws Exception {
		return loginDao.findAll();
	}

	@Override
	public Boolean validUser(String user, String pass) throws Exception {
		Login log = null;
		try {
			log = loginDao.validUser(user, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(log != null) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String insertUser(Login login) throws Exception {
		return loginDao.insertUser(login);
	}

	@Override
	public String update(String id, String user, String pass, String role) throws Exception {
		loginDao.update(id, user, pass, role);
		return "Failed...";
	}

	@Override
	public String deleteById(String id) throws Exception {
		return loginDao.deleteById(id);
	}

	@Override
	public List<?> findUsername(String user) throws Exception {
		return loginDao.findByUsername(user);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return loginDao.existsByEmail(email);
	}
}
