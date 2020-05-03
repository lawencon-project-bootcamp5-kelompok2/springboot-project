package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Subcourse;

public interface SubcourseDao {
	abstract List<?> findAll()throws Exception;
	abstract Subcourse findById(String idSubcourse) throws Exception;
	abstract List<?> findByCourse(String namaCourse) throws Exception;
	abstract List<?> findByKelas(String idKelas) throws Exception;
	abstract List<?> tampilanLihatNilai(String idSubcourse, String idKelas) throws Exception;
	abstract List<?> tampilanInputNilai(String idSubcourse, String idKelas) throws Exception;
	abstract List<?> getNilai(String idSubcourse, String idKelas) throws Exception;
	abstract void insert(Subcourse subcourse)throws Exception;
	abstract Subcourse update(Subcourse subcourse)throws Exception;
	abstract void delete(String idSubcourse)throws Exception;
	abstract String getIdTestBySubcourse(String subcourse) throws Exception;
	abstract String getNamaSubcourse(String subcourse) throws Exception;
	abstract Subcourse validTime(Subcourse subcourse)throws Exception;
}
