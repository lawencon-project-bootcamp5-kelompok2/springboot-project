package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.TestDao;
import com.lawencon.app.springbootproject.model.Test;

@Repository
public class TestDaoImpl extends BaseHibernate implements TestDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Test");
		return q.getResultList();
	}

	@Override
	public Test findById(Test test) throws Exception {
		Query q = em.createQuery("form Test where idTest = :idParam");
		q.setParameter("idParam", test.getIdTest());
		return (Test) q.getSingleResult();
	}

	@Override
	public void insert(Test test) throws Exception {
		em.persist(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		Test test1 = findById(test);
		test1.setWaktuMulai(test.getWaktuMulai());
		test1.setWaktuSelesai(test.getWaktuSelesai());
		em.merge(test1);
		return test1;
	}

	@Override
	public void delete(Test test) throws Exception {
		em.remove(findById(test));
	}
}
