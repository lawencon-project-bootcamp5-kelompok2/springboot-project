package com.lawencon.app.springbootproject.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.service.TrainerService;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerDao trainerDao;

	@Override
	public void createTrainer(Trainer trainer) {
		trainerDao.createTrainer(trainer);
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		trainerDao.updateTrainer(trainer);
	}

	@Override
	public void deleteTrainer(Trainer trainer) {
		trainerDao.deleteTrainer(trainer);
	}

	@Override
	public List<Trainer> findAll() {
		return trainerDao.findAll();
	}

	@Override
	public Trainer findById(Trainer trainer) {
		return trainerDao.findById(trainer);
	}

	@Override
	public List<?> cetakReportTrainer(String id) throws Exception {
		return trainerDao.cetakReportTrainer(id);
	}
	
	
}
