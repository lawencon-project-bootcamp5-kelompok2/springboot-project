package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;

public interface AbsensiDao {
	abstract void insert(Absensi absensi)throws Exception;
	abstract Absensi cekAbsen(Absensi absensi)throws Exception;
	abstract Absensi update(Absensi absensi)throws Exception;
	abstract List<Absensi> findAll()throws Exception;
	List<?> findByIdPertemuanAndStudent(String idPertemuan, String idStudent)throws Exception;
	abstract List<?> findByStudent(String idStudent)throws Exception;
	abstract List<?> findBySubcourseAndKelas(String idSubcourse, String idKelas) throws Exception;
	abstract List<?> cetakAbsen(String idKelas, String idTrainer, String idPertemuan) throws Exception;
}
