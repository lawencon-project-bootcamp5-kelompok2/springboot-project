package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.CourseDao;
import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Trainer;
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
	public Course findById(String idCourse) throws Exception {
		return courseDao.findById(idCourse);
	}

	@Override
	public String insert(Course course) throws Exception {
		return courseDao.insert(course);
	}

	@Override
	public Course update(String idCourse, Trainer trainer, String namaCourse) throws Exception {
		return courseDao.update(idCourse, trainer, namaCourse);
	}

	@Override
	public String delete(String idCourse) throws Exception {
		return courseDao.delete(idCourse);
	}
}
