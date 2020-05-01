package com.lawencon.app.springbootproject.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.service.AdminService;
import com.lawencon.app.springbootproject.service.LoginService;
import com.lawencon.app.springbootproject.service.RoleService;
import com.lawencon.app.springbootproject.service.StudentService;
import com.lawencon.app.springbootproject.service.TrainerService;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	@Qualifier("login_repo_hibernate")
	private LoginDao loginDao;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private RoleService roleService;
	
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
	public Login update(Login login) throws Exception {
		return loginDao.update(login);
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

	@Override
	public void signUp(SignupRequest signUpRequest) throws Exception {
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			studentService.createStudents(signUpRequest);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					try {
						adminService.createAdmin(signUpRequest);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case "trainer":
					try {
						trainerService.createTrainers(signUpRequest);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				default:
					Role userRole = roleService.findRoleStudent();
					roles.add(userRole);
				}
			});
		}
	}
}
