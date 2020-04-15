package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseService {

	abstract List<?> findAll()throws Exception;
	abstract Subcourse findById(Subcourse subcourse) throws Exception;
	abstract void insert(Subcourse subcourse)throws Exception;
	abstract Subcourse update(Subcourse subcourse)throws Exception;
	abstract void delete(Subcourse subcourse)throws Exception;
}
