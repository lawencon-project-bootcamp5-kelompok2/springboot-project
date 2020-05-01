package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.model.Login;

@Repository("login_repo_hibernate")
public class LoginDaoImpl extends BaseHibernate implements LoginDao{

	@Override
	public Login validUser(String user, String pass) throws Exception {
		Query q = em.createQuery("from Login where username = : userParam and password = :passParam")
		.setParameter("userParam", user)
		.setParameter("passParam", pass);
		return (Login) q.getSingleResult();
	}

	@Override
	public String insertUser(Login login) throws Exception {
		em.persist(login);
		return "Succsess to insert";
	}

	@Override
	public Login update(Login login) throws Exception {
		Query q = em.createQuery("from Login where idLogin = :idParam");
		q.setParameter("idParam", login.getIdLogin());
		Login log = new Login();
		log = (Login) q.getSingleResult();
		log.setNama(login.getNama());
		log.setPassword(login.getPassword());
		//log.setRole(role);
		em.merge(log);
		return log;
	}

	@Override
	public String deleteById(String id) throws Exception {
		Query q = em.createQuery("from Login where idLogin = :idParam");
		q.setParameter("idParam", id);
		Login log = new Login();
		log = (Login) q.getSingleResult();
		log.setIdLogin(id);
		em.remove(log);
		return "[USERNAME] AND [PASSWORD] DELETED...";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Login> findAll() throws Exception {
		Query q = em.createQuery("from Login");
		return q.getResultList();
	}

	@Override
	public List<?> findByUsername(String user) throws Exception {
		Query q = em.createQuery("from Login where username = :userParam");
		q.setParameter("userParam", user);
		return q.getResultList();
	}

	@Override
	public Login findByEmail(String email) {
		Query q = em.createQuery(" FROM Login WHERE email =:emailParam");
		q.setParameter("emailParam", email);
		return (Login) q.getSingleResult();
	}

	@Override
	public Boolean existsByEmail(String email) {
		Query q = em.createQuery("SELECT COUNT(*) FROM Login WHERE email =:emailParam");
		q.setParameter("emailParam", email);
		Long result = (Long) q.getSingleResult();
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public String deleteByEmail(String email) throws Exception {
		Query q = em.createQuery("from Login where email = :emailParam");
		q.setParameter("emailParam", email);
		Login log = new Login();
		log = (Login) q.getSingleResult();
		em.remove(findByEmail(log.getEmail()));
		return "DELETED...";
	}

}
