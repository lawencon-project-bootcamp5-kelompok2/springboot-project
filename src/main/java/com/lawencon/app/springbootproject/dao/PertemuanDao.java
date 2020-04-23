package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Pertemuan;

public interface PertemuanDao {
	abstract List<?> findAll()throws Exception;
	abstract Pertemuan findById(String idPertemuan)throws Exception;
	abstract void insert(Pertemuan pertemuan)throws Exception;
	abstract Pertemuan update(Pertemuan pertemuan)throws Exception;
	abstract void delete(String idPertemuan)throws Exception;
	abstract Pertemuan validPertemuan(Pertemuan pertemuan)throws Exception;
}
