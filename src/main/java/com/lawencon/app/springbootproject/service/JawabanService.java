package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanService {
	
	List<?> findAll() throws Exception;

	List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception;

	List<Integer> findAverageStudentFromSubcourse(String idTest) throws Exception;

	List<?> findResultStudentFromAllSubcourse(String idStudent) throws Exception;

	List<Integer> findAverageStudentFromAllSubcourse(String idStudent) throws Exception;

	Jawaban findById(String idJawaban) throws Exception;

	Jawaban update(Jawaban jawaban) throws Exception;

	void insert(Jawaban jawaban) throws Exception;

	void delete(String idJawaban) throws Exception;
}
