package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.AdminDao;
import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.dao.RoleDao;
import com.lawencon.app.springbootproject.model.Admin;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

@Repository
public class AdminDaoImpl extends BaseHibernate implements AdminDao {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public boolean cekAdmin(String email, String pwd) {
		Query q = em.createQuery("SELECT COUNT(*) FROM Admin WHERE emailAdmin =:emailParam AND pwdAdmin =:pwdParam");
		q.setParameter("emailParam", email);
		q.setParameter("pwdParam", pwd);
		int result = (int) q.getSingleResult();
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public void createAdmin(SignupRequest signUpRequest) throws Exception {
		Login user = new Login(signUpRequest.getNama(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		Role userRole = roleDao.findRoleAdmin();
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		Admin admin = new Admin();
		admin.setEmailAdmin(user.getEmail());
		admin.setPwdAdmin(user.getPassword());
		em.persist(admin);
		user.setRoles(roles);
		loginDao.insertUser(user);
	}

	
}
