package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.PertemuanDao;
import com.lawencon.app.springbootproject.model.Pertemuan;

@Repository
public class PertemuanDaoImpl extends BaseHibernate implements PertemuanDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Pertemuan");
		return q.getResultList();
	}

	@Override
	public Pertemuan findById(String idPertemuan) throws Exception {
		Query q = em.createQuery("from Pertemuan where idPertemuan = :idParam");
		q.setParameter("idParam", idPertemuan);
		return (Pertemuan) q.getSingleResult();
	}

	@Override
	public void insert(Pertemuan pertemuan) throws Exception {
		em.persist(pertemuan);
	}

	@Override
	public Pertemuan update(Pertemuan pertemuan) throws Exception {
		Pertemuan p = findById(pertemuan.getIdPertemuan());
		p.setIdSubcourse(pertemuan.getIdSubcourse());
		p.setPertemuan(pertemuan.getPertemuan());
		p.setTanggalPertemuan(pertemuan.getTanggalPertemuan());
		em.merge(p);
		return p;
	}

	@Override
	public void delete(String idPertemuan) throws Exception {
		em.remove(findById(idPertemuan));
	}

	@Override
	public Pertemuan validPertemuan(Pertemuan pertemuan) throws Exception {
		Query q = em.createQuery("from Pertemuan where idSubcourse = :idSubcourse and tanggalPertemuan = :tanggal");
		q.setParameter("idSubcourse", pertemuan.getIdSubcourse());
		q.setParameter("tanggal", pertemuan.getTanggalPertemuan());
		return (Pertemuan) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findBySubcourse(String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "p.id_pertemuan, p.pertemuan, "
				+ "p.tanggal_pertemuan, p.id_subcourse, p.id_materi "
				+ "from pertemuan p where p.id_subcourse = :idParam");
		q.setParameter("idParam", idSubcourse);
		return bMapperHibernate(q.getResultList(), "idPertemuan", "pertemuan", "tanggalPertemuan", "idSubcourse", "idMateri");
	}

}
