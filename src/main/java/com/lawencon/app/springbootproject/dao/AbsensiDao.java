package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.model.Student;

public interface AbsensiDao {
	
	public abstract void insert (Absensi absensi);
	
	public abstract List<Absensi> findAll ();
	
	public abstract Absensi findByStudent (Student id);

}
