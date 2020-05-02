package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.AbsensiDao;
import com.lawencon.app.springbootproject.model.Absensi;
import com.lawencon.app.springbootproject.service.AbsensiService;
import com.lawencon.app.springbootproject.service.KelasService;
import com.lawencon.app.springbootproject.service.TrainerService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class AbsensiServiceImpl implements AbsensiService {
	
	@Autowired
	private AbsensiDao absensiDao;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private KelasService kelasService;

	@Override
	public void insert(Absensi absensi) throws Exception{
		absensiDao.insert(absensi);
	}

	@Override
	public List<Absensi> findAll() throws Exception{
		return absensiDao.findAll();
	}

	@Override
	public List<?> findByStudent(String idStudent) throws Exception{
		return absensiDao.findByStudent(idStudent);
	}

	@Override
	public Absensi update(Absensi absensi) throws Exception {
		return absensiDao.update(absensi);
	}

	@Override
	public String cetakAbsen(String idKelas, String idTrainer, String idPertemuan) throws Exception {
		List<?> data = new ArrayList<>();
		data = absensiDao.cetakAbsen(idKelas, idTrainer, idPertemuan);
		try {
			File file = ResourceUtils.getFile("classpath:report/rekapAbsensi.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\report-absensi-"+trainerService.getNamaTrainer(idTrainer)+"-"+kelasService.getNamaKelas(idKelas)+".pdf");
			return "File berhasil diunduh di Local Disk D";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();			
		}
	}
}
