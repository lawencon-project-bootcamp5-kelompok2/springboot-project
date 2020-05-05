package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Kelas;

public interface KelasService {
	
	List<?> findAll() throws Exception;

	List<?> findAvailableClass(String idStudent) throws Exception;

	List<?> getByTrainer(String id) throws Exception;

	Kelas findById(String idKelas) throws Exception;

	Boolean validKelas(Kelas kelas) throws Exception;

	String getNamaKelas(String id) throws Exception;

	byte[] cetakKelas() throws Exception;

	void insert(Kelas kelas) throws Exception;

	void update(Kelas kelas) throws Exception;

	void delete(String idKelas) throws Exception;
}
