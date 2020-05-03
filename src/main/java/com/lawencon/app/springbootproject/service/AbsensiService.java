package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiService {
	abstract void insert (Absensi absensi) throws Exception;
	abstract Boolean cekAbsen(Absensi absensi) throws Exception;
	abstract List<Absensi> findAll() throws Exception;
	abstract Absensi update(Absensi absensi)throws Exception;
	abstract List<?> findByStudent(String idStudent) throws Exception;
	abstract List<?> findBySubcourseAndKelas(String idSubcourse, String idKelas) throws Exception;
	abstract String cetakAbsen(String idKelas, String idTrainer, String idPertemuan) throws Exception;
}
