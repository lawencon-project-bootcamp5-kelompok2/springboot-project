package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanDao {

	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(String idJawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
	abstract void delete(String idJawaban)throws Exception;
}
