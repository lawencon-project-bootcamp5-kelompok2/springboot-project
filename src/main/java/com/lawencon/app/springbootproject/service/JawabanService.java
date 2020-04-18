package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Jawaban;

public interface JawabanService {
	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(Jawaban jawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
<<<<<<< HEAD
	abstract void delete(Jawaban jawaban)throws Exception;	
||||||| 1095290
	abstract void delete(Jawaban jawaban)throws Exception;
	
//	abstract int getNilaiByStudent(Trainer tr) throws Exception; 
=======
	abstract void delete(Jawaban jawaban)throws Exception;
>>>>>>> 322d9ebab0b74354ce2baae37d13bf133516b729
}
