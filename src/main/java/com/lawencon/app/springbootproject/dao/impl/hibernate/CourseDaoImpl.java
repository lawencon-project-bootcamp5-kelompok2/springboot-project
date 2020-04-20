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
	public Course findById(Course course) throws Exception {
		Query q = em.createQuery("from Course where idCourse = :idParam");
		q.setParameter("idParam", course.getIdCourse());
		return (Course) q.getSingleResult();
	}

	@Override
	public void insert(Course course) throws Exception {
		em.persist(course);
	}

	@Override
	public Course update(Course course) throws Exception {
		Course courses = findById(course);
		courses.setNamaCourse(course.getNamaCourse());
		em.merge(courses);
		return courses;
	}

	@Override
	public void delete(Course course) throws Exception {
		em.remove(findById(course));
	}

	@Override
	public Course validCourse(Course course) throws Exception {
		Query q = em.createQuery("from Course where namaCourse = :namaParam and trainer = :trainerParam");
		q.setParameter("namaParam", course.getNamaCourse());
		q.setParameter("trainerParam", course.getTrainer());
		return (Course) q.getSingleResult();
	}

}
