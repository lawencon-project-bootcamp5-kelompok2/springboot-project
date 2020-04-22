package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiDao {
	abstract void insert(Absensi absensi)throws Exception;
	abstract Absensi update(Absensi absensi)throws Exception;
	abstract List<Absensi> findAll()throws Exception;
	abstract Absensi findByStudent(String idStudent)throws Exception;
	abstract List<?> cetakAbsen(String idSubcourse, String idTrainer) throws Exception;
}
