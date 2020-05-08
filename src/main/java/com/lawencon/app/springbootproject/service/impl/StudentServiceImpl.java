package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.StudentDao;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.service.StudentService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Override
	public void updateStudent(Student student) throws Exception{
		validate(student.getIdStudent());
		studentDao.updateStudent(student);
	}
	
	@Override
	public void updateProfil(Student student) throws Exception {
		validate(student.getIdStudent());
		studentDao.updateProfil(student);
	}

	@Override
	public void deleteStudent(String idStudent) throws Exception{
		validate(idStudent);
		studentDao.deleteStudent(idStudent);
	}

	@Override
	public List<Student> findAll() throws Exception{
		return studentDao.findAll();
	}

	@Override
	public Student findById(String idStudent) throws Exception{
		return studentDao.findById(idStudent);
	}

	@Override
	public byte[] cetakReportStudent(String idStudent, String idKelas, String idCourse) throws Exception {
		List<?> data = new ArrayList<>();
		data = studentDao.cetakReportStudent(idStudent, idKelas, idCourse);
		byte[] pdfReport = null;
		try {
			File file = ResourceUtils.getFile("classpath:report/reportStudent.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return pdfReport;
	}

	@Override
	public Boolean validStudent(SignupRequest signUpRequest) throws Exception {
		Student s = null;
		try {
			s = studentDao.validStudent(signUpRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(s != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean existsByEmail(String email) {
		return studentDao.existsByEmail(email);
	}

	@Override
	public String createStudents(SignupRequest signUpRequest) throws Exception {
		studentDao.createStudents(signUpRequest);
		return "Success";
	}

	@Override
	public Student findByEmail(String email) throws Exception {
		return studentDao.findByEmail(email);
	}

	@Override
	public void validate(String idStudent) throws Exception {
		try {
			studentDao.findById(idStudent);
		} catch (NoResultException e) {
			
		}
	}
}
