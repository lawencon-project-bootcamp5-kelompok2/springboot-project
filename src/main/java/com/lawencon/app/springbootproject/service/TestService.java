package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Test;

public interface TestService {
	
	List<?> findAll() throws Exception;
	
	List<?> findTestBySubcourse(String idSubcourse) throws Exception;

	Test findById(String idTest) throws Exception;

	Test update(Test test) throws Exception;

	Boolean findWaktuSelesai(String idTest) throws Exception;

	Boolean cekTest(Test test) throws Exception;

	String getIdTestByKelas(String idKelas) throws Exception;

	void insert(Test test) throws Exception;

	void delete(String idTest) throws Exception;
}
