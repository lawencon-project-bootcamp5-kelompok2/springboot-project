package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.JawabanDao;
import com.lawencon.app.springbootproject.model.Jawaban;

@Repository
public class JawabanDaoImpl extends BaseHibernate implements JawabanDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Jawaban");
		return q.getResultList();
	}

	@Override
	public Jawaban findById(Jawaban jawaban) throws Exception {
		Query q = em.createQuery("from Jawaban idJawaban = idParam");
		q.setParameter("idParam", jawaban.getIdJawaban());
		return (Jawaban) q.getSingleResult();
	}

	@Override
	public void insert(Jawaban jawaban) throws Exception {
		em.persist(jawaban);
	}

	@Override
	public Jawaban update(Jawaban jawaban) throws Exception {
		Jawaban jawab = findById(jawaban);
		jawab.setNilai(jawaban.getNilai());
		em.merge(jawab);
		return jawab;
	}

	@Override
	public void delete(Jawaban jawaban) throws Exception {
		em.remove(findById(jawaban));
	}
}
