package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.StudentDao;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.service.CourseService;
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
	
	@Autowired
	private CourseService courseService;

	@Override
	public String createStudent(Student student) {
		try {
			if(validStudent(student)==true) {
				return "Data Already Exist";
			}
			else {
				studentDao.createStudent(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	@Override
	public void updateStudent(Student student) throws Exception{
		studentDao.updateStudent(student);
	}

	@Override
	public void deleteStudent(String idStudent) throws Exception{
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
	public String cetakReportStudent(String idStudent, String idCourse) throws Exception {
		List<?> data = new ArrayList<>();
		data = studentDao.cetakReportStudent(idStudent, idCourse);
		try {
			File file = ResourceUtils.getFile("classpath:report/reportStudent.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\report-"+studentDao.getNamaStudent(idStudent)+"-"+courseService.getNamaCourse(idCourse)+".pdf");
			return "File berhasil diunduh di Local Disk D";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();			
		}
	}

	@Override
	public Boolean validStudent(Student student) throws Exception {
		Student s = null;
		try {
			s = studentDao.validStudent(student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(s != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean existsByEmail(String email) {
		return studentDao.existsByEmail(email);
	}
}
