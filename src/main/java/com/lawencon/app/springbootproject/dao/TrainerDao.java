package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerDao {
	
	abstract void createTrainer (Trainer trainer);
	abstract void updateTrainer (Trainer trainer);
	abstract void deleteTrainer (Trainer trainer);
	abstract Trainer validTrainer(Trainer trainer)throws Exception;
	abstract List<Trainer> findAll();
	abstract Trainer findById (Trainer trainer);
	
	abstract List<?> cetakReportTrainer(String id) throws Exception;

}
