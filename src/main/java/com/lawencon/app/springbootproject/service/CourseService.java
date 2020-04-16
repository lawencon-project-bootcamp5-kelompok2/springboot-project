package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;

public interface CourseService {
	abstract List<?> findAll()throws Exception;
	abstract Course findById(Course course)throws Exception;
	abstract void insert(Course course)throws Exception;
	abstract Course update(Course course)throws Exception;
	abstract void delete(Course course)throws Exception;
}
