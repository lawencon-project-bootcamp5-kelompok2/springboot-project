package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

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
	public Test findById(Test test) throws Exception {
		return testDao.findById(test);
	}

	@Override
	public void insert(Test test) throws Exception {
		testDao.insert(test);
	}

	@Override
	public Test update(Test test) throws Exception {
		return testDao.update(test);
	}

	@Override
	public void delete(Test test) throws Exception {
		testDao.delete(test);
	}

}
