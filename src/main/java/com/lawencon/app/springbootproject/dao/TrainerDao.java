package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerDao {
	
	abstract void createTrainer (Trainer trainer)throws Exception;
	abstract void updateTrainer (Trainer trainer)throws Exception;
	abstract void deleteTrainer (String idTrainer)throws Exception;
	abstract Trainer validTrainer(Trainer trainer)throws Exception;
	abstract List<Trainer> findAll()throws Exception;
	abstract Trainer findById (String idTrainer)throws Exception;
	abstract List<?> cetakReportTrainer(String idTrainer, String idTest, String idSubcourse) throws Exception;
	abstract String getNamaTrainer(String id) throws Exception;
}
