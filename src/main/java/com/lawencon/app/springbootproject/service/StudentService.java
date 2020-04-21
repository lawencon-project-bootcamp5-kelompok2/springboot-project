package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;

public interface StudentService {

	abstract String createStudent (Student student);
	abstract void updateStudent (Student student);
	abstract void deleteStudent (Student student);
	abstract List<Student> findAll();
	abstract Student findById (Student student);
	abstract Boolean validStudent(Student student)throws Exception;
	public String cetakReportStudent(String idStudent, String idCourse) throws Exception;
}
