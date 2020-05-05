package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Kelas;

public interface KelasDao {
	
	List<?> findAll() throws Exception;

	List<?> findAvailableClass(String idStudent) throws Exception;

	List<?> cetakKelas() throws Exception;

	List<?> getByTrainer(String id) throws Exception;

	Kelas findById(String idKelas) throws Exception;

	Kelas validKelas(Kelas kelas) throws Exception;

	String getNamaKelas(String id) throws Exception;

	void insert(Kelas kelas) throws Exception;

	void update(Kelas kelas) throws Exception;

	void delete(String idKelas) throws Exception;
}
