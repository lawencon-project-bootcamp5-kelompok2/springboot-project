package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.CourseDao;
import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Trainer;

@Repository
public class CourseDaoImpl extends BaseHibernate implements CourseDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Course");
		return q.getResultList();
	}

	@Override
	public String insert(Course course) throws Exception {
		em.persist(course);
		return "oke";
	}

	@Override
	public Course update(String idCourse, Trainer trainer, String namaCourse) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam");
		q.setParameter("idParam", idCourse);
		Course course = new Course();
		course = (Course) q.getSingleResult();
		course.setIdCourse(idCourse);
		course.setNamaCourse(namaCourse);
		course.setIdTrainer(trainer);
		em.merge(course);
		return course;
	}

	@Override
	public String delete(String idCourse) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam");
		q.setParameter("idParam", idCourse);
		Course course = new Course();
		course = (Course) q.getSingleResult();
		course.setIdCourse(idCourse);
		em.remove(course);
		return null;
	}

	@Override
	public Course findById(String idCourse) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam").setParameter("idParam", idCourse);
		return (Course) q.getSingleResult();
	}
}
