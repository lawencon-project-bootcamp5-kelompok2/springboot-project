package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.NilaiMeanKelasDao;

@Repository
public class NilaiMeanKelasDaoImpl extends BaseHibernate implements NilaiMeanKelasDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listMean(String idCourse) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "sum(nilai_mean)/(select count(id_test)) as mean "
				+ "from "
				+ "nilai_mean_kelas where id_course = :idParam");
		q.setParameter("idParam", idCourse);
		return bMapperHibernate(q.getResultList(), "Mean");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> listMeanOfSubcourseByCourse(String idCourse) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "s.nama_subcourse, nmk.nilai_mean "
				+ "from "
				+ "nilai_mean_kelas nmk "
				+ "join test t on t.id_test = nmk.id_test "
				+ "join subcourse s on s.id_subcourse = t.id_subcourse"
				+ "where nmk.id_course = :idParam");
		q.setParameter("idParam", idCourse);
		return bMapperHibernate(q.getResultList(), "namaSubcourse", "nilaiMean");
	}

}
