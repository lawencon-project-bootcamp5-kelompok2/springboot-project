package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface StudentDao {

	List<Student> findAll() throws Exception;

	List<?> cetakReportStudent(String idStudent, String idKelas, String course) throws Exception;

	Student findById(String idStudent) throws Exception;

	Student validStudent(SignupRequest signUpRequest) throws Exception;

	Student findByEmail(String email);

	String getNamaStudent(String idStudent) throws Exception;

	Boolean existsByEmail(String email);

	void updateStudent(Student student) throws Exception;

	void updateProfil(Student student) throws Exception;

	void deleteStudent(String idStudent) throws Exception;

	void createStudents(SignupRequest signUpRequest) throws Exception;
}
