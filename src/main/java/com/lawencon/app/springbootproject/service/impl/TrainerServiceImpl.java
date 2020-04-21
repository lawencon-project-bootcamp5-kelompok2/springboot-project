package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.SubcourseDao;
import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Trainer;
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
	private SubcourseDao subcourseDao;

	@Override
	public String createTrainer(Trainer trainer) {
		try {
			if(validTrainer(trainer)==true) {
				return "Data Already Exist";
			}
			else {
				trainerDao.createTrainer(trainer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
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
	public String cetakReportTrainer(String idTrainer, String idSubcourse) throws Exception {
		String idTest = subcourseDao.getIdTestBySubcourse(idSubcourse);
		List<?> data = new ArrayList<>();
		data = trainerDao.cetakReportTrainer(idTrainer, idTest, idSubcourse);
		try {
			File file = ResourceUtils.getFile("classpath:report/reportTrainer.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\report-"+trainerDao.getNamaTrainer(idTrainer)+"-"+subcourseDao.getNamaSubcourse(idSubcourse)+".pdf");
			return "File berhasil diunduh di Local Disk D";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();			
		}
	}

	@Override
	public Boolean validTrainer(Trainer trainer) throws Exception {
		Trainer t = null;
		try {
			t = trainerDao.validTrainer(trainer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(t != null) {
			return true;
		}
		return false;
	}
}
