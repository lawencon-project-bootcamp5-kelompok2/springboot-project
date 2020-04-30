package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Test;

public interface TestDao {

	abstract List<?> findAll()throws Exception;
	abstract Test findById(String idTest)throws Exception;
	abstract void insert(Test test)throws Exception;
	abstract Test update(Test test)throws Exception;
	abstract void delete(String idTest)throws Exception;
	abstract List<?> findWaktuSelesai(String idTest)throws Exception;
}
