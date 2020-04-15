package com.lawencon.app.springbootproject.dao.impl.hibernate;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.AdminDao;

@Repository
public class AdminDaoImpl extends BaseHibernate implements AdminDao {

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

	
}
