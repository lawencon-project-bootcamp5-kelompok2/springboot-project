package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.TestDao;
import com.lawencon.app.springbootproject.model.Test;

@Repository
public class TestDaoImpl extends BaseHibernate implements TestDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Test");
		return q.getResultList();
	}

	@Override
	public Test findById(String idTest) throws Exception {
		Query q = em.createQuery("from Test where idTest = :idParam");
		q.setParameter("idParam", idTest);
		return (Test) q.getSingleResult();
	}

	@Override
	public void insert(Test test) throws Exception {
		em.persist(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		Test test1 = findById(test.getIdTest());
		test1.setWaktuMulai(test.getWaktuMulai());
		test1.setWaktuSelesai(test.getWaktuSelesai());
		em.merge(test1);
		return test1;
	}

	@Override
	public void delete(String idTest) throws Exception {
		em.remove(findById(idTest));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findWaktuSelesai(String idTest) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "t.waktu_selesai "
				+ "from "
				+ "test t join pertemuan p on p.id_pertemuan = t.id_pertemuan "
				+ "where t.id_test = :idParam");
		q.setParameter("idParam", idTest);
		return bMapperHibernate(q.getResultList(), "waktuSelesai");
	}

	@Override
	public Test cekTest(Test test) throws Exception {
		Query q = em.createQuery("from Test where idSubcourse = :idParam").
				setParameter("idParam", test.getIdSubcourse());
		return (Test) q.getSingleResult();
	}

	@Override
	public String getIdTestByKelas(String idKelas) throws Exception {
		Query q = em.createNativeQuery("select distinct t.id_test " + 
				"   from test t join subcourse sc on t.id_subcourse = sc.id_subcourse " + 
				"	join course c on c.id_course = sc.id_course " + 
				"	join kelas k on k.id_course = c.id_course " + 
				"	join student_kelas sk on sk.kelas_id_kelas = k.id_kelas " + 
				"	join student s on s.id_student = sk.student_id_student " + 
				"   where k.id_kelas = :kelasParam");
		q.setParameter("kelasParam", idKelas);
		return (String) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findTestBySubcourse(String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "t.id_test, t.waktu_mulai, t.waktu_selesai, t.id_soal, "
				+ "s.nama_subcourse "
				+ "from test t "
				+ "join subcourse s on s.id_subcourse = t.id_subcourse "
				+ "where t.id_subcourse = :idParam").setParameter("idParam", idSubcourse);
		return bMapperHibernate(q.getResultList(), "idTest", "waktuMulai", "waktuSelesai", "idSoal", "namaSubcourse");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findTestByIdSubcourseAndKelas(String idSubcourse, String idKelas) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "t.id_test, s.nama_subcourse, p.pertemuan, t.waktu_mulai, t.waktu_selesai, t.id_soal "
				+ "from test t "
				+ "join subcourse s on s.id_subcourse = t.id_subcourse "
				+ "join course c on c.id_course = s.id_course "
				+ "join kelas k on k.id_course = c.id_course "
				+ "join pertemuan p on p.id_pertemuan = t.id_pertemuan "
				+ "where t.id_subcourse = :idSubcourse and k.id_kelas = :idKelas").
				setParameter("idSubcourse", idSubcourse).
				setParameter("idKelas", idKelas);
		return bMapperHibernate(q.getResultList(), "idTest", "namaSubcourse", "pertemuan", "waktuMulai", "waktuSelesai", "idSoal");
	}
}
