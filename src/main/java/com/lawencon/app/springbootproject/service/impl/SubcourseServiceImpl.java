package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.SubcourseDao;
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
	public Subcourse findById(String idSubcourse) throws Exception {
		return subcourseDao.findById(idSubcourse);
	}

	@Override
	public String insert(Subcourse subcourse) throws Exception {
		if(validTime(subcourse)==true) {
			return "Schedule had been Book with another trainer, choose another Time";
		}
		else {
			subcourseDao.insert(subcourse);
		}
		return "Success Insert";
	}

	@Override
	public Subcourse update(Subcourse subcourse) throws Exception {
		return subcourseDao.update(subcourse);
	}

	@Override
	public void delete(String idSubcourse) throws Exception {
		subcourseDao.delete(idSubcourse);
	}

	@Override
	public List<?> findByCourse(String idCourse) throws Exception {
		return subcourseDao.findByCourse(idCourse);
	}

	@Override
	public Boolean validTime(Subcourse subcourse) throws Exception {
		Subcourse subCourse = null;
		try {
			subCourse = subcourseDao.validTime(subcourse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(subCourse != null) {
			return true;
		}
		return false;
	}
	
}
