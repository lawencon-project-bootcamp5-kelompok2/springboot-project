package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface StudentService {

	abstract void createStudent (Student student)throws Exception;
	abstract void updateStudent (Student student)throws Exception;
	abstract void updateProfil (Student student)throws Exception;
	abstract void deleteStudent (String idStudent)throws Exception;
	abstract List<Student> findAll()throws Exception;
	abstract Student findById (String idStudent)throws Exception;
	abstract Boolean validStudent(SignupRequest signUpRequest)throws Exception;
	abstract byte[] cetakReportStudent(String idStudent, String idKelas) throws Exception;
	abstract String createStudents(SignupRequest signUpRequest)throws Exception;
	abstract boolean existsByEmail(String email);
	abstract Student findByEmail(String email) throws Exception;
}
