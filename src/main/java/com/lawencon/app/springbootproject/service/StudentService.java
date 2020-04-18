package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;

public interface StudentService {

	public abstract void createStudent (Student student);
	public abstract void updateStudent (Student student);
	public abstract void deleteStudent (Student student);
	
	public abstract List<Student> findAll();
	public abstract Student findById (Student student);
	
	public String cetakReportStudent(String id) throws Exception;
}
