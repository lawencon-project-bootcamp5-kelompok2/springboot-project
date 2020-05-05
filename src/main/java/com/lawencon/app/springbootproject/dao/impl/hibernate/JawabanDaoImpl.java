package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.JawabanDao;
import com.lawencon.app.springbootproject.model.Jawaban;

@Repository
public class JawabanDaoImpl extends BaseHibernate implements JawabanDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Jawaban");
		return q.getResultList();
	}

	@Override
	public Jawaban findById(String idJawaban) throws Exception {
		Query q = em.createQuery("from Jawaban idJawaban = idParam");
		q.setParameter("idParam", idJawaban);
		return (Jawaban) q.getSingleResult();
	}

	@Override
	public void insert(Jawaban jawaban) throws Exception {
		em.persist(jawaban);
	}

	@Override
	public Jawaban update(Jawaban jawaban) throws Exception {
		Jawaban jawab = findById(jawaban.getIdJawaban());
		jawab.setNilai(jawaban.getNilai());
		em.merge(jawab);
		return jawab;
	}

	@Override
	public void delete(String idJawaban) throws Exception {
		em.remove(findById(idJawaban));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "s.nama_student, j.nilai, s2.nama_subcourse "
				+ "from "
				+ "jawaban j join student s on s.id_student = j.id_student "
				+ "join test t on t.id_test = j.id_test "
				+ "join subcourse s2 on s2.id_subcourse = t.id_subcourse "
				+ "where j.id_test = :idTest and j.id_student = :idStudent");
		q.setParameter("idTest", idTest).setParameter("idStudent", idStudent);
		return bMapperHibernate(q.getResultList(), "namaStudent", "nilai", "namaSubcourse");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAverageStudentFromSubcourse(String idTest) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "sum(nilai)/(select count(id_test = :idParam)) as mean "
				+ "from jawaban");
		q.setParameter("idParam", idTest);
		List<Map<String, Object>> listResult = bMapperHibernate(q.getResultList(), "Mean");
		if(!listResult.isEmpty()) {
			return listResult;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findResultStudentFromAllSubcourse(String idStudent) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "s2.nama_student, j.nilai, s.nama_subcourse "
				+ "from "
				+ "jawaban j join student s2 on j.id_student = s2.id_student "
				+ "join test t on t.id_test = j.id_test "
				+ "join subcourse s on s.id_subcourse = t.id_subcourse "
				+ "where j.id_student = :idParam");
		q.setParameter("idParam", idStudent);
		return bMapperHibernate(q.getResultList(), "namaStudent", "nilai", "namaSubcourse");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAverageStudentFromAllSubcourse(String idStudent) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "sum(j.nilai)/(select count(t.id_test)) as mean "
				+ "from "
				+ "jawaban j join test t on t.id_test = j.id_test "
				+ "where j.id_student = :idParam");
		q.setParameter("idParam", idStudent);
		List<Map<String, Object>> listResult = bMapperHibernate(q.getResultList(), "Mean");
		if(!listResult.isEmpty()) {
			return listResult;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> createResultAverageStudentFromSubcourse(Jawaban jawaban) throws Exception {
		Query q = em.createNativeQuery("insert into "
				+ "nilai_mean_kelas (id_course, id_test, nama_subcourse, nilai_mean) "
				+ "select distinct "
				+ "c.id_course, j.id_test, s.nama_subcourse, "
				+ "(select sum(nilai)/(select count(id_test)) as mean from jawaban) "
				+ "from jawaban j join test t on j.id_test = t.id_test "
				+ "join subcourse s on t.id_subcourse = s.id_subcourse "
				+ "join course c on s.id_course = c.id_course "
				+ "where j.id_test = :idParam");
		q.setParameter("idParam", jawaban.getIdTest());
		return bMapperHibernate(q.getResultList(), "idCourse","idTest", "namaSubcourse","nilaiMean");
	}	
}
