package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Kelas;

public interface KelasService {
	abstract List<?> findAll()throws Exception;
	abstract List<?> findAvailableClass(String idStudent) throws Exception;
	abstract Kelas findById(String idKelas)throws Exception;
	abstract String insert(Kelas kelas)throws Exception;
	abstract void update(Kelas kelas)throws Exception;
	abstract void delete(String idKelas)throws Exception;
	abstract Boolean validKelas(Kelas kelas)throws Exception;
	abstract String getNamaKelas(String id) throws Exception;
	abstract String cetakKelas() throws Exception;
	abstract List<?> getByTrainer(String id) throws Exception;
}
