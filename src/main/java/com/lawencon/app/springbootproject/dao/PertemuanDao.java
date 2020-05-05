package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Pertemuan;

public interface PertemuanDao {
	
	List<?> findAll() throws Exception;

	List<?> findBySubcourse(String idSubcourse) throws Exception;

	Pertemuan findById(String idPertemuan) throws Exception;

	Pertemuan update(Pertemuan pertemuan) throws Exception;

	Pertemuan validPertemuan(Pertemuan pertemuan) throws Exception;

	void insert(Pertemuan pertemuan) throws Exception;

	void delete(String idPertemuan) throws Exception;
}
