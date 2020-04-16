package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Jawaban;
import com.lawencon.app.springbootproject.model.Trainer;

public interface JawabanService {
	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(Jawaban jawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
	abstract void delete(Jawaban jawaban)throws Exception;
	
//	abstract int getNilaiByStudent(Trainer tr) throws Exception; 
}
