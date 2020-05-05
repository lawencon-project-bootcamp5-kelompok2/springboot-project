package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface StudentService {

	List<Student> findAll() throws Exception;

	Student findById(String idStudent) throws Exception;

	Student findByEmail(String email) throws Exception;

	Boolean validStudent(SignupRequest signUpRequest) throws Exception;

	Boolean existsByEmail(String email);

	byte[] cetakReportStudent(String idStudent, String idKelas) throws Exception;

	String createStudents(SignupRequest signUpRequest) throws Exception;

	void updateStudent(Student student) throws Exception;

	void updateProfil(Student student) throws Exception;

	void deleteStudent(String idStudent) throws Exception;
}
