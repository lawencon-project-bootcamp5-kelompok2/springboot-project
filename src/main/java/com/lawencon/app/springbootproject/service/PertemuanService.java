package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Pertemuan;

public interface PertemuanService {
	
	List<?> findAll() throws Exception;

	List<?> findBySubcourse(String idSubcourse) throws Exception;

	Pertemuan findById(String idPertemuan) throws Exception;

	Pertemuan update(Pertemuan pertemuan) throws Exception;

	String insert(Pertemuan pertemuan) throws Exception;

	Boolean validPertemuan(Pertemuan pertemuan) throws Exception;

	void delete(String idPertemuan) throws Exception;
	
	void validate(String idPertemuan) throws Exception;
}
