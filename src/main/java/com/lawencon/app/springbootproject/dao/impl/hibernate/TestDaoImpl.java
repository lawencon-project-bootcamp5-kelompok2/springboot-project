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
	public Test findById(String idTest) throws Exception {
		Query q = em.createQuery("form Test where idTest = :idParam");
		q.setParameter("idParam", idTest);
		return (Test) q.getSingleResult();
	}

	@Override
	public void insert(Test test) throws Exception {
		em.persist(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		Test test1 = findById(test.getIdTest());
		test1.setWaktuMulai(test.getWaktuMulai());
		test1.setWaktuSelesai(test.getWaktuSelesai());
		em.merge(test1);
		return test1;
	}

	@Override
	public void delete(String idTest) throws Exception {
		em.remove(findById(idTest));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findWaktuSelesai(String idTest) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "t.waktu_selesai "
				+ "from "
				+ "test t join pertemuan p on p.id_pertemuan = t.id_pertemuan "
				+ "where t.id_test = :idParam");
		q.setParameter("idParam", idTest);
		return bMapperHibernate(q.getResultList(), "waktuSelesai");
	}
}
