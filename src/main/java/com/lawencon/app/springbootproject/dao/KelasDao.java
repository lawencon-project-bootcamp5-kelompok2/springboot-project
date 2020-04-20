package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Kelas;

public interface KelasDao {
	abstract List<?> findAll()throws Exception;
	abstract Kelas findById(Kelas kelas)throws Exception;
	abstract void insert(Kelas kelas)throws Exception;
	abstract void update(Kelas kelas)throws Exception;
	abstract void delete(Kelas kelas)throws Exception;
}
