package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface StudentDao {
	
	abstract void createStudent (Student student)throws Exception;
	abstract void updateStudent (Student student)throws Exception;
	abstract void deleteStudent (String idStudent)throws Exception;
	abstract List<Student> findAll()throws Exception;
	abstract Student findById (String idStudent)throws Exception;
	abstract List<?> cetakReportStudent(String id)throws Exception;
	abstract Student validStudent(SignupRequest signUpRequest)throws Exception;
	abstract List<?> cetakReportStudent(String idStudent, String idCourse) throws Exception;
	abstract String getNamaStudent(String idStudent) throws Exception;
	abstract void createStudents(SignupRequest signUpRequest)throws Exception;
	Student findByEmail(String email);
	Boolean existsByEmail(String email);
}
