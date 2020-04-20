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
	public Course findById(Course course) throws Exception {
		return courseDao.findById(course);
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
	public void delete(Course course) throws Exception {
		courseDao.delete(course);
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
}
