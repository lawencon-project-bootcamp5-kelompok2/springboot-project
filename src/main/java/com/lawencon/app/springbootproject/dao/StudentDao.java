package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;

public interface StudentDao {
	
	abstract void createStudent (Student student);
	abstract void updateStudent (Student student);
	abstract void deleteStudent (Student student);
	abstract List<Student> findAll();
	abstract Student findById (Student student);
	abstract List<?> cetakReportStudent(String id)throws Exception;
	abstract Student validStudent(Student student)throws Exception;
}
