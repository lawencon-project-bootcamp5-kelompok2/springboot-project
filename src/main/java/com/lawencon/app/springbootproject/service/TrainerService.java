package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface TrainerService {

	abstract String createTrainers(SignupRequest signUpRequest)throws Exception;
	abstract void updateTrainer (Trainer trainer)throws Exception;
	abstract void deleteTrainer (String idTrainer)throws Exception;
	abstract Boolean validTrainers(SignupRequest signUpRequest)throws Exception;
	abstract List<Trainer> findAll()throws Exception;
	abstract Trainer findById (String idTrainer)throws Exception;
	abstract String cetakReportTrainer(String idTrainer, String idSubcourse) throws Exception;
	abstract Trainer getNamaTrainer(String idTrainer) throws Exception;
	abstract Trainer findByNamaAndEmail(String search) throws Exception;
}
