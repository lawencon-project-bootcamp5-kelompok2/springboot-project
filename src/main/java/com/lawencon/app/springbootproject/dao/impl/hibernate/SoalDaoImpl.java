package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.io.IOException;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.SoalDao;
import com.lawencon.app.springbootproject.exception.FileStorageException;
import com.lawencon.app.springbootproject.exception.ELearningFileNotFoundException;
import com.lawencon.app.springbootproject.model.Soal;

@Repository
public class SoalDaoImpl extends BaseHibernate implements SoalDao{
	
	@Override
	public Soal upload(MultipartFile soal) {
		String fileName = StringUtils.cleanPath(soal.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Soal file = new Soal(fileName, soal.getContentType(), soal.getBytes());
			em.persist(file);
			return file;
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	@Override
	public Soal getFile(String fileId) throws Exception {
		try {
			Query q = em.createQuery("from Soal where idSoal = :idParam").setParameter("idParam", fileId);
			return (Soal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			new ELearningFileNotFoundException("File not found with id : "+fileId);
		}
		return null;
	}
}
