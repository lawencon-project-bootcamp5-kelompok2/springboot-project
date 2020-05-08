package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface TrainerDao {
	
	List<Trainer> findAll() throws Exception;

	List<Trainer> findByNamaAndEmail(String search) throws Exception;

	List<?> cetakReportTrainer(String idKelas, String idSubcourse) throws Exception;

	Trainer validTrainers(SignupRequest signUpRequest) throws Exception;

	Trainer findByEmail(String email) throws Exception;

	Trainer findById(String idTrainer) throws Exception;

	String getNamaTrainer(String idTrainer) throws Exception;

	void createTrainers(SignupRequest signUpRequest) throws Exception;

	void updateTrainer(Trainer trainer) throws Exception;

	void deleteTrainer(String idTrainer) throws Exception;
}
