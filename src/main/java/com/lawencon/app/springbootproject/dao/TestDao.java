package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Test;

public interface TestDao {
	
	List<?> findAll() throws Exception;

	List<?> findWaktuSelesai(String idTest) throws Exception;
	
	List<?> findTestBySubcourse(String idSubcourse) throws Exception;
	
	List<?> findTestByIdSubcourseAndKelas(String idSubcourse, String idKelas) throws Exception;

	Test findById(String idTest) throws Exception;

	Test update(Test test) throws Exception;

	Test cekTest(Test test) throws Exception;

	String getIdTestByKelas(String idKelas) throws Exception;

	void insert(Test test) throws Exception;

	void delete(String idTest) throws Exception;
}
