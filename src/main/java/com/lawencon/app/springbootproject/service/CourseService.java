package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;

public interface CourseService {
	abstract List<?> findAll()throws Exception;
	abstract List<?> findByTrainer(String idTrainer) throws Exception;
	abstract Course findById(String idCourse)throws Exception;
	abstract String insert(Course course)throws Exception;
	abstract Course update(Course course)throws Exception;
	abstract void delete(String idCourse)throws Exception;
	abstract Boolean validCourse(Course course)throws Exception;
	abstract String getIdCourse(String id) throws Exception;
	abstract String getNamaCourse(String id) throws Exception;
	abstract List<?> getRekapJadwal(String id) throws Exception;
}
