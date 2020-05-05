package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;

public interface CourseDao {
	
	List<?> findAll() throws Exception;

	List<?> getRekapJadwal(String id) throws Exception;

	List<?> findByTrainer(String idTrainer) throws Exception;

	Course findById(String idCourse) throws Exception;

	Course update(Course course) throws Exception;

	Course validCourse(Course course) throws Exception;

	String getIdCourse(String id) throws Exception;

	String getNamaCourse(String id) throws Exception;

	void insert(Course course) throws Exception;

	void delete(String idCourse) throws Exception;
}
