package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.app.springbootproject.dao.TestDao;
import com.lawencon.app.springbootproject.model.Test;
import com.lawencon.app.springbootproject.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;

	@Override
	public List<?> findAll() throws Exception {
		return testDao.findAll();
	}

	@Override
	public Test findById(String idTest) throws Exception {
		return testDao.findById(idTest);
	}

	@Override
	public void insert(Test test) throws Exception {
		if(cekTest(test)==true) {
			throw new Exception("Test Has Been Set on This Subcourse !");
		}
		testDao.insert(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		validate(test.getIdTest());
		return testDao.update(test);
	}

	@Override
	public void delete(String idTest) throws Exception {
		validate(idTest);
		testDao.delete(idTest);
	}

	@Override
	public Boolean findWaktuSelesai(String idTest) throws Exception {
		List<?> listWaktuSelesai = null;
		try {
			listWaktuSelesai = testDao.findWaktuSelesai(idTest);
		} catch (Exception e) {
			
		}
		if(listWaktuSelesai != null) {
			return true;
		}
		return false;
	}

	@Override
	public Boolean cekTest(Test test) throws Exception {
		Test t = null;
		try {
			t = testDao.cekTest(test);
		} catch (Exception e) {
			
		}
		if(t!=null) {
			return true;
		}
		return false;
	}

	@Override
	public String getIdTestByKelas(String idKelas) throws Exception {
		return testDao.getIdTestByKelas(idKelas);
	}

	@Override
	public List<?> findTestBySubcourse(String idSubcourse) throws Exception {
		return testDao.findTestBySubcourse(idSubcourse);
	}

	@Override
	public List<?> findTestByIdSubcourseAndKelas(String idSubcourse, String idKelas) throws Exception {
		return testDao.findTestByIdSubcourseAndKelas(idSubcourse, idKelas);
	}

	@Override
	public void validate(String idTest) throws Exception {
		try {
			testDao.findById(idTest);
		} catch (NoResultException e) {
			throw new Exception("Wrong id");
		}
	}
}
