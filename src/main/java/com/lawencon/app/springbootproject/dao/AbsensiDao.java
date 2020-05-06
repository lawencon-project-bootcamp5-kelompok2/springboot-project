package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiDao {

	List<Absensi> findAll() throws Exception;
	
	List<?> findByIdPertemuan(String idPertemuan) throws Exception;
	
	List<?> findByIdPertemuanAndKelas(String idPertemuan, String idSubcourse, String idKelas) throws Exception;

	List<?> findByIdPertemuanAndStudent(String idPertemuan, String emailStudent) throws Exception;

	List<?> findByStudent(String idStudent) throws Exception;

	List<?> findPending(String idSubcourse, String idKelas) throws Exception;

	List<?> cetakAbsen(String idKelas, String idPertemuan) throws Exception;

	Absensi cekAbsen(Absensi absensi) throws Exception;

	Absensi update(Absensi absensi) throws Exception;
	
	Absensi findById(String idAbsensi) throws Exception;

	void insert(Absensi absensi) throws Exception;
}
