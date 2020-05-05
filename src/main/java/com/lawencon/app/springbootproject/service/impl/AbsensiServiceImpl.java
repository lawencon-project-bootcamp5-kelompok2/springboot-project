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
	public byte[] cetakAbsen(String idKelas, String idPertemuan) throws Exception {
		List<?> data = new ArrayList<>();
		data = absensiDao.cetakAbsen(idKelas, idPertemuan);
		byte[] pdfReport = null;
		try {
			File file = ResourceUtils.getFile("classpath:report/rekapAbsensi.jrxml");
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
	public List<?> findPending(String idSubcourse, String idKelas) throws Exception {
		return absensiDao.findPending(idSubcourse, idKelas);
	}

	@Override
	public Boolean cekAbsen(Absensi absensi) throws Exception {
		Absensi absen = null;
		try {
			absen = absensiDao.cekAbsen(absensi);
		} catch (Exception e) {
			
		}
		if(absen != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<?> findByIdPertemuanAndStudent(String idPertemuan, String emailStudent) throws Exception {
		return absensiDao.findByIdPertemuanAndStudent(idPertemuan, emailStudent);
	}

	@Override
	public Absensi findById(String idAbsensi) throws Exception {
		return absensiDao.findById(idAbsensi);
	}

	@Override
	public List<?> findByIdPertemuan(String idPertemuan) throws Exception {
		return absensiDao.findByIdPertemuan(idPertemuan);
	}
}
