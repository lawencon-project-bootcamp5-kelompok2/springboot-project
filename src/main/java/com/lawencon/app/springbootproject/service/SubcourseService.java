package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseService {

	List<?> findAll() throws Exception;

	List<?> findByCourse(String namaCourse) throws Exception;

	List<?> findByKelas(String idKelas) throws Exception;
	
	List<?> findTanggalSelesai(String idSubcourse) throws Exception;

	List<?> tampilanLihatNilai(String idSubcourse, String idKelas) throws Exception;

	List<?> tampilanInputNilai(String idSubcourse, String idKelas) throws Exception;

	Subcourse update(Subcourse subcourse) throws Exception;

	Subcourse findById(String idSubcourse) throws Exception;

	String insert(Subcourse subcourse) throws Exception;

	String getIdTestBySubcourse(String subcourse) throws Exception;

	String getNamaSubcourse(String subcourse) throws Exception;

	Boolean validTime(Subcourse subcourse) throws Exception;

	void delete(String idSubcourse) throws Exception;
	
	void validate(String idSubcourse) throws Exception;
}
