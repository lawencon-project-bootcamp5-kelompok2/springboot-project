package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanService {
	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(String idJawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
	abstract void delete(String idJawaban)throws Exception;
	abstract List<?> findResultStudentFromSubcourse(String idTest, String idStudent) throws Exception;
	abstract List<Integer> findAverageStudentFromSubcourse(String idTest)throws Exception;
	abstract List<?> findResultStudentFromAllSubcourse(String idStudent)throws Exception;
	abstract List<Integer> findAverageStudentFromAllSubcourse(String idStudent)throws Exception;
}
