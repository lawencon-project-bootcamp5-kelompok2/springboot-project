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
		subCourse.setDeskripsi(subcourse.getDeskripsi());
		em.merge(subCourse);
		return subCourse;
	}

	@Override
	public void delete(String idSubcourse) throws Exception {
		em.remove(findById(idSubcourse));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByCourse(String namaCourse) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "s.id_subcourse, s.nama_subcourse, s.tanggal_mulai, s.tanggal_selesai, s.id_course "
				+ "from "
				+ "subcourse s join course c on c.id_course = s.id_course "
				+ "where c.nama_course = :namaParam").
				setParameter("namaParam", namaCourse);
		return bMapperHibernate(q.getResultList(), "idSubcourse", "namaSubcourse", "tanggalMulai", "tanggalSelesai", "idCourse");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> tampilanLihatNilai(String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select distinct " + 
				"	sc.nama_subcourse, s.npm, s.nama_student, a.status, j.nilai " + 
				"from " + 
				"	subcourse sc join absensi a on sc.id_subcourse = a.id_subcourse " + 
				"	join student s on s.id_student = a.id_student " + 
				"	join jawaban j on j.id_student = s.id_student " + 
				"	join course c on c.id_course = sc.id_course " + 
				"	join kelas k on k.id_course = c.id_course " + 
				"	join student_kelas sk on sk.student_id_student = s.id_student " + 
				"	join pertemuan p on p.id_pertemuan = a.id_pertemuan " + 
				"	join test t on t.id_pertemuan = p.id_pertemuan " +
				"	where " + 
				"	sk.kelas_id_kelas = :kelasParam and sc.id_subcourse = :subParam")
				.setParameter("kelasParam", idKelas).setParameter("subParam", idSubcourse);
		return bMapperHibernate(q.getResultList(), "namaSubcourse", "npm", "namaStudent", "status", "nilai");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByKelas(String idKelas) throws Exception {
		Query q = em.createNativeQuery("select sc.id_subcourse, sc.nama_subcourse, sc.tanggal_selesai " + 
				"from subcourse sc join kelas k on sc.id_course = k.id_course " + 
				"where k.id_kelas = :idParam")
				.setParameter("idParam", idKelas);
		return bMapperHibernate(q.getResultList(), "idSubcourse", "namaSubcourse", "tanggalSelesai");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> tampilanInputNilai(String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select s.npm, s.nama_student " + 
				"from kelas k join student_kelas sk on k.id_kelas = sk.kelas_id_kelas " + 
				"	join student s on s.id_student = sk.student_id_student " + 
				"	join subcourse sc on sc.id_course = k.id_course " + 
				"where sc.id_subcourse = :subParam and k.id_kelas = :kelasParam")
				.setParameter("subParam", idSubcourse).setParameter("kelasParam", idKelas);
		return bMapperHibernate(q.getResultList(), "npm", "namaStudent");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getNilai(String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select j.nilai, s.npm " + 
				"from jawaban j join student s on j.id_student = s.id_student " + 
				"	join student_kelas sk on sk.student_id_student = s.id_student " + 
				"	join kelas k on k.id_kelas = sk.kelas_id_kelas " + 
				"	join course c on c.id_course = k.id_course " + 
				"	join subcourse sc on sc.id_course = c.id_course " + 
				"where sc.id_subcourse = :subParam and k.id_kelas = :kelasParam")
				.setParameter("subParam", idSubcourse).setParameter("kelasParam", idKelas);
		return bMapperHibernate(q.getResultList(), "nilai", "npm");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findTanggalSelesai(String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select s.tanggal_selesai "
				+ "from subcourse s where s.id_subcourse = :idParam");
		return bMapperHibernate(q.getResultList(), "tanggalSelesai");
	}

}
