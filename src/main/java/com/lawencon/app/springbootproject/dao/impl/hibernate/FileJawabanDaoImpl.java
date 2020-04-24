package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.FileJawabanDao;
import com.lawencon.app.springbootproject.exception.FileStorageException;
import com.lawencon.app.springbootproject.exception.ELearningFileNotFoundException;
import com.lawencon.app.springbootproject.model.FileJawaban;

@Repository
public class FileJawabanDaoImpl extends BaseHibernate implements FileJawabanDao{
	
	@Override
	public FileJawaban upload(MultipartFile fileJawaban) {
		String fileName = StringUtils.cleanPath(fileJawaban.getOriginalFilename());
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			FileJawaban file = new FileJawaban(fileName, fileJawaban.getContentType(), fileJawaban.getBytes(), String.valueOf(dateFormat.format(date)));
			em.persist(file);
			return file;
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	@Override
	public FileJawaban getFile(String fileId) throws Exception {
		try {
			Query q = em.createQuery("from FileJawaban where idFileJawaban = :idParam").setParameter("idParam", fileId);
			return (FileJawaban) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			new ELearningFileNotFoundException("File not found with id : "+fileId);
		}
		return null;
	}

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from FileJawaban");
		return q.getResultList();
	}

	@Override
	public FileJawaban validTimer(FileJawaban uploadTime) throws Exception {
		Query q = em.createQuery("From FileJawaban where uploadTime = :uploadParam");
		q.setParameter("uploadTime", uploadTime.getUploadTime());
		return (FileJawaban) q.getSingleResult();
	}
}
