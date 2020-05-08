package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;

public interface CourseService {
	List<?> findAll() throws Exception;

	List<?> findByTrainer(String idTrainer) throws Exception;

	List<?> getRekapJadwal(String id) throws Exception;

	Course findById(String idCourse) throws Exception;

	Course update(Course course) throws Exception;

	String insert(Course course) throws Exception;

	String getIdCourse(String id) throws Exception;

	String getNamaCourse(String id) throws Exception;

	Boolean validCourse(Course course) throws Exception;

	void delete(String idCourse) throws Exception;
	
	void validate(String idCourse) throws Exception;

}
