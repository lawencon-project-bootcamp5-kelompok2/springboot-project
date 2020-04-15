package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Trainer;

public interface CourseService {
	abstract List<?> findAll()throws Exception;
	abstract Course findById(String idCourse)throws Exception;
	abstract String insert(Course course)throws Exception;
	abstract Course update(String idCourse, Trainer trainer, String namaCourse)throws Exception;
	abstract String delete(String idCourse)throws Exception;
}
