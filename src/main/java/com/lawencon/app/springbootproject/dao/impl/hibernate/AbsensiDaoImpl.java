package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.AbsensiDao;
import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;

@Repository
public class AbsensiDaoImpl extends BaseHibernate implements AbsensiDao {

	@Override
	public void insert(Absensi absensi) {
		em.persist(absensi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Absensi> findAll() {
		Query q = em.createQuery(" from Absensi");
		return q.getResultList();
	}

	@Override
	public Absensi findByStudent(Student id) {
		Query q = em.createQuery(" from Absensi where idStudent =:idParam");
		q.setParameter("idParam", id.getIdStudent());
		return (Absensi) q.getSingleResult();
	}

	
}
