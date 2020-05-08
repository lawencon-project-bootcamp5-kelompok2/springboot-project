package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.model.payload.request.SignupRequest;

public interface TrainerService {
	
	List<Trainer> findAll() throws Exception;

	List<Trainer> findByNamaAndEmail(String search) throws Exception;

	Trainer findById(String idTrainer) throws Exception;

	Trainer findByEmail(String email) throws Exception;
	
	void validateId(String idTrainer) throws Exception;

	Boolean validTrainers(SignupRequest signUpRequest) throws Exception;

	String createTrainers(SignupRequest signUpRequest) throws Exception;

	byte[] cetakReportTrainer(String idTrainer, String idKelas) throws Exception;

	String getNamaTrainer(String idTrainer) throws Exception;

	void updateTrainer(Trainer trainer) throws Exception;

	void deleteTrainer(String idTrainer) throws Exception;

}
