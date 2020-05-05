package com.lawencon.app.springbootproject.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanDao {
	
	List<?> findAll() throws Exception;

	List<?> createResultAverageStudentFromSubcourse(Jawaban jawaban) throws Exception;

	List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception;

	List<Map<String, Object>> findAverageStudentFromSubcourse(String idTest) throws Exception;

	List<?> findResultStudentFromAllSubcourse(String idStudent) throws Exception;

	List<Map<String, Object>> findAverageStudentFromAllSubcourse(String idStudent) throws Exception;

	Jawaban findById(String idJawaban) throws Exception;

	Jawaban update(Jawaban jawaban) throws Exception;

	void delete(String idJawaban) throws Exception;

	void insert(Jawaban jawaban) throws Exception;
}
