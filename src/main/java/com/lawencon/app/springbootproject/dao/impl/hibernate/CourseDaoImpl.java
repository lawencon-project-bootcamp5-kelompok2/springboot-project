package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.CourseDao;
import com.lawencon.app.springbootproject.model.Course;

@Repository
public class CourseDaoImpl extends BaseHibernate implements CourseDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Course");
		return q.getResultList();
	}

	@Override
	public Course findById(String idCourse) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam");
		q.setParameter("idParam", idCourse);
		return (Course) q.getSingleResult();
	}

	@Override
	public void insert(Course course) throws Exception {
		em.persist(course);
	}

	@Override
	public Course update(Course course) throws Exception {
		Course courses = findById(course.getIdCourse());
		courses.setNamaCourse(course.getNamaCourse());
		courses.setDeskripsi(course.getDeskripsi());
		em.merge(courses);
		return courses;
	}

	@Override
	public void delete(String idCourse) throws Exception {
		em.remove(findById(idCourse));
	}

	@Override
	public Course validCourse(Course course) throws Exception {
		Query q = em.createQuery("from Course where namaCourse = :namaParam and trainer = :trainerParam");
		q.setParameter("namaParam", course.getNamaCourse());
		q.setParameter("trainerParam", course.getTrainer());
		return (Course) q.getSingleResult();
	}
	
	@Override
	public String getIdCourse(String id) throws Exception {
		Query q = em.createNativeQuery("SELECT id_course from course where id_course = :idParam");
		q.setParameter("idParam", id);
		return (String) q.getSingleResult();
	}

	@Override
	public String getNamaCourse(String id) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_course from course where id_course = :idParam");
		q.setParameter("idParam", id);
		return (String) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getRekapJadwal(String id) throws Exception {
		Query q = em.createNativeQuery("SELECT "
				+ "k.kode_kelas, t.nama_trainer, s.nama_subcourse, s.tanggal_mulai "
				+ "FROM kelas k "
				+ "JOIN subcourse s ON k.id_course = s.id_course "
				+ "JOIN course c ON c.id_course = s.id_course "
				+ "JOIN trainer t ON t.id_trainer = c.id_trainer " + 
				"WHERE c.id_course =:idParam");
		q.setParameter("idParam", id);
		return bMapperHibernate(q.getResultList(), "kodeKelas", "namaTrainer", "namaSubcourse", "tanggalMulai");
	}

}
