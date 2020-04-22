package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerService {

	abstract String createTrainer (Trainer trainer)throws Exception;
	abstract void updateTrainer (Trainer trainer)throws Exception;
	abstract void deleteTrainer (String idTrainer)throws Exception;
	abstract Boolean validTrainer(Trainer trainer)throws Exception;
	abstract List<Trainer> findAll()throws Exception;
	abstract Trainer findById (String idTrainer)throws Exception;
	abstract String cetakReportTrainer(String idTrainer, String idSubcourse) throws Exception;
}
