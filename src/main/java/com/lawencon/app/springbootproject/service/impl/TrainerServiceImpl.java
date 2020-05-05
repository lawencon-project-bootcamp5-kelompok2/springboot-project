package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Trainer;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;
import com.lawencon.app.springbootproject.service.TestService;
import com.lawencon.app.springbootproject.service.TrainerService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerDao trainerDao;
	
	@Autowired
	private TestService testService;

	@Override
	public void updateTrainer(Trainer trainer) throws Exception{
		trainerDao.updateTrainer(trainer);
	}

	@Override
	public void deleteTrainer(String idTrainer) throws Exception{
		trainerDao.deleteTrainer(idTrainer);
	}

	@Override
	public List<Trainer> findAll() throws Exception{
		return trainerDao.findAll();
	}

	@Override
	public Trainer findById(String idTrainer) throws Exception{
		return trainerDao.findById(idTrainer);
	}

	@Override
	public byte[] cetakReportTrainer(String idTrainer, String idKelas) throws Exception {
		String idTest = testService.getIdTestByKelas(idKelas);
		List<?> data = new ArrayList<>();
		data = trainerDao.cetakReportTrainer(idTrainer, idTest, idKelas);
		byte[] pdfReport = null;
		try {
			File file = ResourceUtils.getFile("classpath:report/reportTrainer.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			pdfReport = JasperExportManager.exportReportToPdf(jasperPrint);
			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return pdfReport;
	}

	@Override
	public String getNamaTrainer(String idTrainer) throws Exception {
		return trainerDao.getNamaTrainer(idTrainer);
	}

	@Override
	public String createTrainers(SignupRequest signUpRequest) throws Exception {
		trainerDao.createTrainers(signUpRequest);	
		return "Success";
	}

	@Override
	public Boolean validTrainers(SignupRequest signUpRequest) throws Exception {
		Trainer t = null;
		try {
			t = trainerDao.validTrainers(signUpRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(t != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Trainer> findByNamaAndEmail(String search) throws Exception {
		return trainerDao.findByNamaAndEmail(search);
	}

	@Override
	public Trainer findByEmail(String email) throws Exception {
		return trainerDao.findByEmail(email);
	}
}
