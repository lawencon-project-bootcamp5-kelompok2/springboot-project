package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;

public interface CourseDao {
	abstract List<?> findAll()throws Exception;
	abstract Course findById(String idCourse)throws Exception;
	abstract void insert(Course course)throws Exception;
	abstract Course update(Course course)throws Exception;
	abstract void delete(String idCourse)throws Exception;
	abstract Course validCourse(Course course)throws Exception;
	abstract String getIdCourse(String id) throws Exception;
	abstract String getNamaCourse(String id) throws Exception;
	abstract List<?> getRekapJadwal(String id) throws Exception;
}
