package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseService {

	abstract List<?> findAll()throws Exception;
	abstract Subcourse findById(String idSubcourse) throws Exception;
	abstract List<?> findByCourse(String idCourse) throws Exception;
	abstract String insert(Subcourse subcourse)throws Exception;
	abstract Subcourse update(Subcourse subcourse)throws Exception;
	abstract void delete(String idSubcourse)throws Exception;
	abstract Boolean validTime(Subcourse subcourse)throws Exception;
}
