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
		Absensi absen = new Absensi();
		absen.setIdStudent(absensi.getIdStudent());
		absen.setIdSubcourse(absensi.getIdSubcourse());
		absen.setPertemuan(absensi.getPertemuan());
		absen.setStatus("Pending");
		em.persist(absen);
	}
	
	@Override
	public Absensi cekAbsen(Absensi absensi) throws Exception {
		Query q = em.createQuery("from Absensi where idStudent = :idParam and idSubcourse = :idSub and pertemuan = :idPert");
		q.setParameter("idParam", absensi.getIdStudent()).
		setParameter("idSub", absensi.getIdSubcourse()).
		setParameter("idPert", absensi.getPertemuan());
		return (Absensi) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Absensi> findAll() throws Exception{
		Query q = em.createQuery(" from Absensi");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findPending(String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select distinct s.nama_subcourse , p.pertemuan , st.nama_student from " + 
				"	absensi a join subcourse s on a.id_subcourse = s.id_subcourse " + 
				"	join course c on c.id_course = s.id_course " + 
				"	join kelas k on k.id_course = c.id_course " + 
				"	join student st on st.id_student = a.id_student " + 
				"	join student_kelas sk on sk.kelas_id_kelas = k.id_kelas " + 
				"	join pertemuan p on p.id_pertemuan = a.id_pertemuan " + 
				"where s.id_subcourse = :subParam and k.id_kelas = :kelasParam and a.status = 'Pending'");
		q.setParameter("subParam", idSubcourse).setParameter("kelasParam", idKelas);
		return bMapperHibernate(q.getResultList(), "namaSubcourse", "pertemuan", "namaStudent");
	}	

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByStudent(String idStudent) throws Exception{
		Query q = em.createNativeQuery("select "
				+ "a.id_absensi, a.id_student, a.id_subcourse, "
				+ "a.id_pertemuan, a.status, a.tanggal "
				+ "from "
				+ "absensi a join student s on a.id_student = s.id_student "
				+ "where s.id_student = :idParam");
		q.setParameter("idParam", idStudent);
		return bMapperHibernate(q.getResultList(), "idAbsensi", "idStudent", "idSubcourse", "idPertemuan", "status", "tanggal");
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
	public List<?> cetakAbsen(String idKelas, String idPertemuan) throws Exception {
		Query q = em.createNativeQuery("select distinct p.pertemuan, k.kode_kelas, st.nama_student, p.tanggal_pertemuan, "
				+ "a.status, t.nama_trainer, s.nama_subcourse " + 
				"   from absensi a join subcourse s on a.id_subcourse = s.id_subcourse " + 
				"	join course c on c.id_course = s.id_course " + 
				"	join kelas k on k.id_course = c.id_course " + 
				"	join student st on st.id_student = a.id_student " + 
				"	join student_kelas sk on sk.kelas_id_kelas = k.id_kelas " + 
				"	join pertemuan p on p.id_pertemuan = a.id_pertemuan " + 
				"	join trainer t on t.id_trainer = c.id_trainer " + 
				"where k.id_kelas = :kelasParam and p.id_pertemuan = :pertemuanParam")
				.setParameter("kelasParam", idKelas).setParameter("pertemuanParam", idPertemuan);
		return bMapperHibernate(q.getResultList(), "pertemuan", "kodeKelas", "namaStudent", "tanggalPertemuan", "status", "namaTrainer", "namaSubcourse");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByIdPertemuanAndStudent(String idPertemuan, String emailStudent) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "a.id_absensi, s.nama_student, s2.nama_subcourse, "
				+ "p.pertemuan, a.status, a.tanggal "
				+ "from absensi a "
				+ "join pertemuan p on p.id_pertemuan = a.id_pertemuan "
				+ "join student s on s.id_student = a.id_student "
				+ "join subcourse s2 on s2.id_subcourse = a.id_subcourse "
				+ "where a.id_pertemuan = :idParam and s.email = :emailParam");
		q.setParameter("idParam", idPertemuan).setParameter("emailParam", emailStudent);
		return bMapperHibernate(q.getResultList(), "idAbsensi", "namaStudent", "namaSubcourse", "pertemuan", "status", "tanggal");
	}

	@Override
	public Absensi findById(String idAbsensi) throws Exception {
		Query q = em.createQuery("from Absensi where idAbsensi = :idParam").
				setParameter("idParam", idAbsensi);
		return (Absensi) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByIdPertemuan(String idPertemuan) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "a.id_absensi, s.nama_student, s2.nama_subcourse, "
				+ "p.pertemuan, a.status, a.tanggal "
				+ "from absensi a "
				+ "join pertemuan p on p.id_pertemuan = a.id_pertemuan "
				+ "join student s on s.id_student = a.id_student "
				+ "join subcourse s2 on s2.id_subcourse = a.id_subcourse "
				+ "where a.id_pertemuan = :idParam");
		q.setParameter("idParam", idPertemuan);
		return bMapperHibernate(q.getResultList(), "idAbsensi", "namaStudent", "namaSubcourse", "pertemuan", "status", "tanggal");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByIdPertemuanAndKelas(String idPertemuan, String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select distinct st.nama_student, p.tanggal_pertemuan , a.status from "
				+ " absensi a join subcourse s on a.id_subcourse = s.id_subcourse "
				+ " join course c on c.id_course = s.id_course " 
				+ " join kelas k on k.id_course = c.id_course "
				+ " join student st on st.id_student = a.id_student "
				+ " join student_kelas sk on sk.kelas_id_kelas = k.id_kelas "
				+ " join pertemuan p on p.id_pertemuan = a.id_pertemuan "
				+ " where p.id_pertemuan =:pertemuanParam and s.id_subcourse = :subParam and k.id_kelas = :kelasParam");
		q.setParameter("pertemuanParam", idPertemuan).
		setParameter("subParam", idSubcourse).
		setParameter("kelasParam", idKelas);
		return bMapperHibernate(q.getResultList(), "namaStudent", "tanggalPertemuan", "status");
	}
}
