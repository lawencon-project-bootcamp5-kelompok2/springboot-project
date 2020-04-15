package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Test;

public interface TestService {
	abstract List<?> findAll()throws Exception;
	abstract Test findById(Test test)throws Exception;
	abstract void insert(Test test)throws Exception;
	abstract Test update(Test test)throws Exception;
	abstract void delete(Test test)throws Exception;
}
