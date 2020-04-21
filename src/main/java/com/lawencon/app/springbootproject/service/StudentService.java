package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;

public interface StudentService {

	abstract String createStudent (Student student)throws Exception;
	abstract void updateStudent (Student student)throws Exception;
	abstract void deleteStudent (Student student)throws Exception;
	abstract List<Student> findAll()throws Exception;
	abstract Student findById (Student student)throws Exception;
	abstract Boolean validStudent(Student student)throws Exception;
	abstract String cetakReportStudent(String idStudent, String idCourse) throws Exception;
}
