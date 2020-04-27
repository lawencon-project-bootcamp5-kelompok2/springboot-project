package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

public interface TrainerDao {
	
	abstract void createTrainers(SignupRequest signUpRequest)throws Exception;
	abstract void updateTrainer (Trainer trainer)throws Exception;
	abstract void deleteTrainer (String idTrainer)throws Exception;
	abstract Trainer validTrainers(SignupRequest signUpRequest)throws Exception;
	abstract List<Trainer> findAll()throws Exception;
	abstract Trainer findById (String idTrainer)throws Exception;
	abstract List<?> cetakReportTrainer(String idTrainer, String idTest, String idSubcourse) throws Exception;
	abstract Trainer getNamaTrainer(String idTrainer) throws Exception;
}
