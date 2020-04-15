package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;

public interface StudentDao {
	
	public abstract void createStudent (Student student);
	public abstract void updateStudent (Student student);
	public abstract void deleteStudent (Student student);
	
	public abstract List<Student> findAll();
	public abstract Student findById (Student student);

}
