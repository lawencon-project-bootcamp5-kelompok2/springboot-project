package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiService {
	List<Absensi> findAll() throws Exception;

	List<?> findByIdPertemuanAndStudent(String idPertemuan, String emailStudent) throws Exception;

	List<?> findByStudent(String idStudent) throws Exception;

	List<?> findPending(String idSubcourse, String idKelas) throws Exception;

	byte[] cetakAbsen(String idKelas, String idPertemuan) throws Exception;

	Absensi update(Absensi absensi) throws Exception;

	Boolean cekAbsen(Absensi absensi) throws Exception;

	void insert(Absensi absensi) throws Exception;
}
