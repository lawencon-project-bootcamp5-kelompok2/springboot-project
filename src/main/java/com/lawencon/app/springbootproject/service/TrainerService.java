package com.lawencon.app.springbootproject.service;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerService {

	public abstract void createTrainer (Trainer trainer);
	public abstract void updateTrainer (Trainer trainer);
	public abstract void deleteTrainer (Trainer trainer);
	
	public abstract List<Trainer> findAll();
	public abstract Trainer findById (Trainer trainer);
	
	abstract List<?> cetakReportTrainer(String id) throws Exception;
}
