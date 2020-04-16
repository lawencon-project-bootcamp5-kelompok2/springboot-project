package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.SubcourseDao;
import com.lawencon.app.springbootproject.model.Subcourse;

@Repository
public class SubcourseDaoImpl extends BaseHibernate implements SubcourseDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Subcourse");
		return q.getResultList();
	}

	@Override
	public Subcourse findById(Subcourse subcourse) throws Exception {
		Query q = em.createQuery("from Subcourse where idSubcourse = :idParam");
		q.setParameter("idParam", subcourse.getIdSubcourse());
		return (Subcourse) q.getSingleResult();
	}

	@Override
	public void insert(Subcourse subcourse) throws Exception {
		em.persist(subcourse);
	}

	@Override
	public Subcourse update(Subcourse subcourse) throws Exception {
		Subcourse subCourse = findById(subcourse);
		subCourse.setNamaSubcourse(subcourse.getNamaSubcourse());
		subCourse.setMateri(subcourse.getMateri());
		em.merge(subCourse);
		return subCourse;
	}

	@Override
	public void delete(Subcourse subcourse) throws Exception {
		em.remove(findById(subcourse));
	}

}