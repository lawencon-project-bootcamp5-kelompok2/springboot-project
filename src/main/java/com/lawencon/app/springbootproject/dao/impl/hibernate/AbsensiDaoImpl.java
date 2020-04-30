package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.AbsensiDao;
import com.lawencon.app.springbootproject.model.Absensi;

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
	public Absensi findByStudent(String idStudent) throws Exception{
		Query q = em.createQuery(" from Absensi where idStudent =:idParam");
		q.setParameter("idParam", idStudent);
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
	public List<?> cetakAbsen(String idKelas, String idTrainer, String idPertemuan) throws Exception {
		Query q = em.createNativeQuery("select distinct p.pertemuan, k.kode_kelas, s.nama_student, p.tanggal_pertemuan, a.status, t.nama_trainer, sc.nama_subcourse " + 
				"from student s join absensi a on s.id_student = a.id_student " + 
				"join subcourse sc on sc.id_subcourse = a.id_subcourse " + 
				"join course c on c.id_course = sc.id_course " + 
				"join trainer t on t.id_trainer = c.id_trainer " + 
				"join pertemuan p on p.id_subcourse = sc.id_subcourse " + 
				"join kelas k on k.id_course = c.id_course " +
				"where k.id_kelas = :kelasParam and t.id_trainer = :trainerParam " + 
				"and p.id_pertemuan = :pertemuanParam")
				.setParameter("kelasParam", idKelas).setParameter("trainerParam", idTrainer).setParameter("pertemuanParam", idPertemuan);

		return bMapperHibernate(q.getResultList(), "pertemuan", "kodeKelas", "namaStudent", "tanggalPertemuan", "status", "namaTrainer", "namaSubcourse");
	}	
}
