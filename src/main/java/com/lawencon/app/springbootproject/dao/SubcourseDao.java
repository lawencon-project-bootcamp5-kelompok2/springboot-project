package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseDao {
	
	List<?> findAll() throws Exception;

	List<?> findByCourse(String namaCourse) throws Exception;

	List<?> findByKelas(String idKelas) throws Exception;
	
	List<?> findTanggalSelesai(String idSubcourse) throws Exception;

	List<?> tampilanLihatNilai(String idSubcourse, String idKelas) throws Exception;

	List<?> tampilanInputNilai(String idSubcourse, String idKelas) throws Exception;

	List<?> getNilai(String idSubcourse, String idKelas) throws Exception;

	Subcourse findById(String idSubcourse) throws Exception;

	Subcourse update(Subcourse subcourse) throws Exception;

	Subcourse validTime(Subcourse subcourse) throws Exception;

	String getIdTestBySubcourse(String subcourse) throws Exception;

	String getNamaSubcourse(String subcourse) throws Exception;

	void insert(Subcourse subcourse) throws Exception;

	void delete(String idSubcourse) throws Exception;
}
