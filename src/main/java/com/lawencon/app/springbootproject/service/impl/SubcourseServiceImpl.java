package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.SubcourseDao;
import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Subcourse;
import com.lawencon.app.springbootproject.service.SubcourseService;

@Service
@Transactional
public class SubcourseServiceImpl implements SubcourseService{

	@Autowired
	private SubcourseDao subcourseDao;

	@Override
	public List<?> findAll() throws Exception {
		return subcourseDao.findAll();
	}

	@Override
	public Subcourse findById(Subcourse subcourse) throws Exception {
		return subcourseDao.findById(subcourse);
	}

	@Override
	public void insert(Subcourse subcourse) throws Exception {
		subcourseDao.insert(subcourse);
	}

	@Override
	public Subcourse update(Subcourse subcourse) throws Exception {
		return subcourseDao.update(subcourse);
	}

	@Override
	public void delete(Subcourse subcourse) throws Exception {
		subcourseDao.delete(subcourse);
	}

	@Override
	public Subcourse findByCourse(Course course) throws Exception {
		return subcourseDao.findByCourse(course);
	}
	
}
