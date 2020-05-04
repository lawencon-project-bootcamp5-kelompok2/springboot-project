package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Pertemuan;

public interface PertemuanService {
	abstract List<?> findAll()throws Exception;
	abstract Pertemuan findById(String idPertemuan)throws Exception;
	abstract List<?> findBySubcourse(String idSubcourse)throws Exception;
	abstract String insert(Pertemuan pertemuan)throws Exception;
	abstract Pertemuan update(Pertemuan pertemuan)throws Exception;
	abstract void delete(String idPertemuan)throws Exception;
	abstract Boolean validPertemuan(Pertemuan pertemuan)throws Exception;
}
