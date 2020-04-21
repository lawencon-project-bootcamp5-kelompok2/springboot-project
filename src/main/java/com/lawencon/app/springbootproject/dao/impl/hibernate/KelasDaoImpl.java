package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.KelasDao;
import com.lawencon.app.springbootproject.model.Kelas;

@Repository
public class KelasDaoImpl extends BaseHibernate implements KelasDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Kelas");
		return q.getResultList();
	}

	@Override
	public Kelas findById(Kelas kelas) throws Exception {
		Query q = em.createQuery("from Kelas where idKelas = :idParam");
		q.setParameter("idParam", kelas.getIdKelas());
		return (Kelas) q.getSingleResult();
	}

	@Override
	public void insert(Kelas kelas) throws Exception {
		em.persist(kelas);
	}

	@Override
	public void update(Kelas kelas) throws Exception {
		Kelas k = findById(kelas);
		k.setKodeKelas(kelas.getKodeKelas());
		k.setCourse(kelas.getCourse());
		k.setOpenKelas(kelas.getOpenKelas());
		em.merge(k);
	}

	@Override
	public void delete(Kelas kelas) throws Exception {
		em.remove(findById(kelas));
	}

	@Override
	public Kelas validKelas(Kelas kelas) throws Exception {
		Query q = em.createQuery("from Kelas where openKelas = :openParam");
		q.setParameter("openParam", kelas.getOpenKelas());
		return (Kelas) q.getSingleResult();
	}
}
