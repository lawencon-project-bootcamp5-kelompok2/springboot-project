package com.lawencon.app.springbootproject.dao;

import java.util.List;

import com.lawencon.app.springbootproject.model.Trainer;

public interface TrainerDao {
	
	public abstract void createTrainer (Trainer trainer);
	public abstract void updateTrainer (Trainer trainer);
	public abstract void deleteTrainer (Trainer trainer);
	
	public abstract List<Trainer> findAll();
	public abstract Trainer findById (Trainer trainer);

}
