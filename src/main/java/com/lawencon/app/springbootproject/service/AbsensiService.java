package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;

public interface AbsensiService {
	abstract void insert (Absensi absensi) throws Exception;
	abstract List<Absensi> findAll() throws Exception;
	abstract Absensi update(Absensi absensi)throws Exception;
	abstract Absensi findByStudent(Student id) throws Exception;
}
