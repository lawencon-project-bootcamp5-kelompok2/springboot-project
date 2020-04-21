package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerService {

	public abstract String createTrainer (Trainer trainer);
	public abstract void updateTrainer (Trainer trainer);
	public abstract void deleteTrainer (Trainer trainer);
	abstract Boolean validTrainer(Trainer trainer)throws Exception;
	public abstract List<Trainer> findAll();
	public abstract Trainer findById (Trainer trainer);
	abstract String cetakReportTrainer(String idTrainer, String idSubcourse) throws Exception;
}
