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

	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakAbsen(String idSubcourse, String idTrainer) throws Exception {
		Query q = em.createNativeQuery("select distinct " + 
				"	sc.nama_subcourse, s.nama_student, a.tanggal, a.status, t.nama_trainer " + 
				"from \r\n" + 
				"	student s \r\n" + 
				"	join absensi a on s.id_student = a.id_student " + 
				"	join subcourse sc on sc.id_subcourse = a.id_subcourse " +	
				"	join course c on c.id_course = sc.id_course \r\n" + 
				"	join trainer t on t.id_trainer = c.id_trainer " + 
				"	where sc.id_subcourse = :idSubcourse and t.id_trainer = :idTrainer ")
				.setParameter("idSubcourse", idSubcourse).setParameter("idTrainer", idTrainer);
		return bMapperHibernate(q.getResultList(), "namaSubcourse", "namaStudent", "tanggal", "status", "namaTrainer");
	}	
}
