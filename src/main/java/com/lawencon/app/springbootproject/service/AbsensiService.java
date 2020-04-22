package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiService {
	abstract void insert (Absensi absensi) throws Exception;
	abstract List<Absensi> findAll() throws Exception;
	abstract Absensi update(Absensi absensi)throws Exception;
	abstract Absensi findByStudent(String idStudent) throws Exception;
	abstract String cetakAbsen(String idSubcourse, String idTrainer) throws Exception;
}
