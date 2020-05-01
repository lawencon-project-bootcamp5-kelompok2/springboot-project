package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.CourseDao;
import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.service.CourseService;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseDao courseDao;

	@Override
	public List<?> findAll() throws Exception {
		return courseDao.findAll();
	}
	
	@Override
	public List<?> findByTrainer(String idTrainer) throws Exception {
		return courseDao.findByTrainer(idTrainer);
	}

	@Override
	public Course findById(String idCourse) throws Exception {
		return courseDao.findById(idCourse);
	}

	@Override
	public String insert(Course course) throws Exception {
		if(validCourse(course)==true) {
			return "Data Already Exist";
		}
		else {
			courseDao.insert(course);
		}
		return "Success";
	}

	@Override
	public Course update(Course course) throws Exception {
		return courseDao.update(course);
	}

	@Override
	public void delete(String idCourse) throws Exception {
		courseDao.delete(idCourse);
	}

	@Override
	public Boolean validCourse(Course course) throws Exception {
		Course c = null;
		try {
			c = courseDao.validCourse(course);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(c != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getIdCourse(String id) throws Exception {
		return courseDao.getIdCourse(id);
	}

	@Override
	public String getNamaCourse(String id) throws Exception {
		return courseDao.getNamaCourse(id);
	}

	@Override
	public List<?> getRekapJadwal(String id) throws Exception {
		return courseDao.getRekapJadwal(id);
	}
}
