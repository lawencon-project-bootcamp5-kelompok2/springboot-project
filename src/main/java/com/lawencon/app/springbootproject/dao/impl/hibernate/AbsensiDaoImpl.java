package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.AbsensiDao;
import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;

@Repository
public class AbsensiDaoImpl extends BaseHibernate implements AbsensiDao {

	@Override
	public void insert(Absensi absensi) throws Exception{
		em.persist(absensi);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Absensi> findAll() throws Exception{
		Query q = em.createQuery(" from Absensi");
		return q.getResultList();
	}

	@Override
	public Absensi findByStudent(Student id) throws Exception{
		Query q = em.createQuery(" from Absensi where idStudent =:idParam");
		q.setParameter("idParam", id.getIdStudent());
		return (Absensi) q.getSingleResult();
	}

	@Override
	public Absensi update(Absensi absensi) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		Query q = em.createQuery("from Absensi where idStudent = :idParam and tanggal is null");
		q.setParameter("idParam", absensi.getIdStudent());
		try {
			absensi = (Absensi) q.getSingleResult();
			absensi.setTanggal(String.valueOf(dateFormat.format(date)));
			absensi.setStatus("Hadir");
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.merge(absensi);
		return absensi;
	}	
}
