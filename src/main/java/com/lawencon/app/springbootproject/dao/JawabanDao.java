package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Course;
import com.lawencon.app.springbootproject.model.Jawaban;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.model.Subcourse;
import com.lawencon.app.springbootproject.model.Test;
import com.lawencon.app.springbootproject.model.Trainer;

public interface JawabanDao {

	abstract List<?> findAll()throws Exception;
	abstract Jawaban findById(Jawaban jawaban)throws Exception;
	abstract void insert(Jawaban jawaban)throws Exception;
	abstract Jawaban update(Jawaban jawaban)throws Exception;
	abstract void delete(Jawaban jawaban)throws Exception;
}
