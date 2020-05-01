package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Kelas;

public interface KelasDao {
	abstract List<?> findAll()throws Exception;
	abstract Kelas findById(String idKelas)throws Exception;
	abstract void insert(Kelas kelas)throws Exception;
	abstract void update(Kelas kelas)throws Exception;
	abstract void delete(String idKelas)throws Exception;
	abstract Kelas validKelas(Kelas kelas)throws Exception;
	abstract String getNamaKelas(String id) throws Exception;
	abstract List<?> cetakKelas() throws Exception;
	abstract List<?> getByTrainer(String id) throws Exception;
}
