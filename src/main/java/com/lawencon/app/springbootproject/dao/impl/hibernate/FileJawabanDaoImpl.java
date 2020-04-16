package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.io.IOException;

import javax.persistence.Query;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.FileJawabanDao;
//import com.lawencon.app.springbootproject.dao.repo.MateriRepo;
import com.lawencon.app.springbootproject.exception.FileStorageException;
import com.lawencon.app.springbootproject.exception.ELearningFileNotFoundException;
import com.lawencon.app.springbootproject.model.FileJawaban;

@Repository
public class FileJawabanDaoImpl extends BaseHibernate implements FileJawabanDao{
	
//	@Autowired
//	private MateriRepo materiRepo;
	
	@Override
	public FileJawaban upload(MultipartFile fileJawaban) {
		String fileName = StringUtils.cleanPath(fileJawaban.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			FileJawaban file = new FileJawaban(fileName, fileJawaban.getContentType(), fileJawaban.getBytes());
			em.persist(file);
			return file;
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	@Override
	public FileJawaban getFile(String fileId) throws Exception {
		try {
			Query q = em.createQuery("from Materi where id = :idParam").setParameter("idParam", fileId);
			return (FileJawaban) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			new ELearningFileNotFoundException("File not found with id : "+fileId);
		}
		return null;
//		return materiRepo.findById(fileId).orElseThrow(() -> new ELearningFileNotFoundException("File not found with id " + fileId));
	}
}