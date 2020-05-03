package com.lawencon.app.springbootproject.dao;

import java.util.List;
import java.util.Map;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanDao {

	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(String idJawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
	abstract void delete(String idJawaban)throws Exception;
	abstract List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception;
	abstract List<Map<String, Object>> findAverageStudentFromSubcourse(String idTest)throws Exception;
	abstract List<?> findResultStudentFromAllSubcourse(String idStudent)throws Exception;
	abstract List<Map<String, Object>> findAverageStudentFromAllSubcourse(String idStudent)throws Exception;
}
