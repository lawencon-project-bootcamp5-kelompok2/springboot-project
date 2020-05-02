package com.lawencon.app.springbootproject.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.lawencon.app.springbootproject.dao.KelasDao;
import com.lawencon.app.springbootproject.model.Kelas;
import com.lawencon.app.springbootproject.service.KelasService;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
@Transactional
public class KelasServiceImpl implements KelasService{

	@Autowired
	private KelasDao kelasDao;

	@Override
	public List<?> findAll() throws Exception {
		return kelasDao.findAll();
	}
	
	@Override
	public List<?> findAvailableClass(String idStudent) throws Exception {
		return kelasDao.findAvailableClass(idStudent);
	}

	@Override
	public Kelas findById(String idKelas) throws Exception {
		return kelasDao.findById(idKelas);
	}

	@Override
	public String insert(Kelas kelas) throws Exception {
		if(validKelas(kelas)==true) {
			return "Class already exist on that schedule";
		}
		else {
			kelasDao.insert(kelas);
		}
		return "Success";
	}

	@Override
	public void update(Kelas kelas) throws Exception {
		kelasDao.update(kelas);
	}

	@Override
	public void delete(String idKelas) throws Exception {
		kelasDao.delete(idKelas);
	}

	@Override
	public Boolean validKelas(Kelas kelas) throws Exception {
		Kelas k = null;
		try {
			k = kelasDao.validKelas(kelas);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(k != null) {
			return true;
		}
		return false;
	}

	@Override
	public String getNamaKelas(String id) throws Exception {
		return kelasDao.getNamaKelas(id);
	}

	@Override
	public String cetakKelas() throws Exception {
		List<?> data = new ArrayList<>();
		data = kelasDao.cetakKelas();
		try {
			File file = ResourceUtils.getFile("classpath:report/daftarKelas.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data,false);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperExportManager.exportReportToPdfFile(jasperPrint, "D:\\daftar-kelas.pdf");
			return "File berhasil diunduh di Local Disk D";
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();			
		}
	}

	@Override
	public List<?> getByTrainer(String id) throws Exception {
		return kelasDao.getByTrainer(id);
	}
}
