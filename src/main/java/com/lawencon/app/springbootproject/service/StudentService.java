package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface StudentService {

	abstract String createStudent (Student student)throws Exception;
	abstract void updateStudent (Student student)throws Exception;
	abstract void deleteStudent (String idStudent)throws Exception;
	abstract List<Student> findAll()throws Exception;
	abstract Student findById (String idStudent)throws Exception;
	abstract Boolean validStudent(Student student)throws Exception;
	abstract String cetakReportStudent(String idStudent, String idCourse) throws Exception;
	abstract void createStudents(SignupRequest signUpRequest)throws Exception;
	abstract boolean existsByEmail(String email);
	abstract Student findByEmail(String email) throws Exception;
}
