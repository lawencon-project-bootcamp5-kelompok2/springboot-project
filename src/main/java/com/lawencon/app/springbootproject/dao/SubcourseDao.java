package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseDao {
	abstract List<?> findAll()throws Exception;
	abstract Subcourse findById(Subcourse subcourse) throws Exception;
	abstract Subcourse findByCourse(Course course) throws Exception;
	abstract void insert(Subcourse subcourse)throws Exception;
	abstract Subcourse update(Subcourse subcourse)throws Exception;
	abstract void delete(Subcourse subcourse)throws Exception;
}
