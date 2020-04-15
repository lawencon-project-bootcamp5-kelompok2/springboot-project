package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.StudentDao;
import com.lawencon.app.springbootproject.model.Student;

@Repository
public class StudentDaoImpl extends BaseHibernate implements StudentDao {

	@Override
	public void createStudent(Student student) {
		em.persist(student);
	}

	@Override
	public void updateStudent(Student student) {
		Student s = findById(student);
		s.setNamaStudent(student.getNamaStudent());
		s.setCourse(student.getCourse());
		em.merge(s);
	}

	@Override
	public void deleteStudent(Student student) {
		em.remove(findById(student));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() {
		Query q = em.createQuery(" FROM Student");
		return q.getResultList();
	}

	@Override
	public Student findById(Student student) {
		Query q = em.createQuery(" FROM Student WHERE idStudent =:idParam");
		q.setParameter("idParam", student.getIdStudent());
		return (Student) q.getSingleResult();
	}

	
}
