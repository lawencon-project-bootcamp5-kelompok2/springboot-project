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
	public Subcourse findById(String idSubcourse) throws Exception {
		Query q = em.createQuery("from Subcourse where idSubcourse = :idParam");
		q.setParameter("idParam", idSubcourse);
		return (Subcourse) q.getSingleResult();
	}

	@Override
	public void insert(Subcourse subcourse) throws Exception {
		em.persist(subcourse);
	}

	@Override
	public Subcourse update(Subcourse subcourse) throws Exception {
		Subcourse subCourse = findById(subcourse.getIdSubcourse());
		subCourse.setNamaSubcourse(subcourse.getNamaSubcourse());
		subCourse.setTanggalMulai(subcourse.getTanggalMulai());
		subCourse.setTanggalSelesai(subcourse.getTanggalSelesai());
		em.merge(subCourse);
		return subCourse;
	}

	@Override
	public void delete(String idSubcourse) throws Exception {
		em.remove(findById(idSubcourse));
	}

	@Override
	public List<?> findByCourse(String idCourse) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam").
				setParameter("idParam", idCourse);
		return q.getResultList();
	}
	
	@Override
	public String getIdTestBySubcourse(String subcourse) throws Exception {
		Query q = em.createNativeQuery("SELECT id_test FROM test WHERE id_subcourse = :idParam").setParameter("idParam", subcourse);
		return (String) q.getSingleResult();
	}
	
	@Override
	public String getNamaSubcourse(String subcourse) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_subcourse FROM subcourse WHERE id_subcourse = :idParam").setParameter("idParam", subcourse);
		return (String) q.getSingleResult();
	}

	@Override
	public Subcourse validTime(Subcourse subcourse) throws Exception {
		Query q = em.createQuery("from Subcourse where tanggalMulai = :mulai and tanggalSelesai = :selesai");
		q.setParameter("mulai", subcourse.getTanggalMulai());
		q.setParameter("selesai", subcourse.getTanggalSelesai());
		return (Subcourse) q.getSingleResult();
	}

}
