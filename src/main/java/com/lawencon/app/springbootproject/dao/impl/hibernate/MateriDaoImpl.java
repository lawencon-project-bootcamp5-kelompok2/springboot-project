package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.io.IOException;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.MateriDao;
import com.lawencon.app.springbootproject.dao.repo.MateriRepo;
import com.lawencon.app.springbootproject.exception.FileStorageException;
import com.lawencon.app.springbootproject.exception.MyFileNotFoundException;
import com.lawencon.app.springbootproject.model.Materi;

@Repository
public class MateriDaoImpl extends BaseHibernate implements MateriDao{
	
	@Autowired
	private MateriRepo materiRepo;
	
	@Override
	public Materi upload(MultipartFile materi) {
		String fileName = StringUtils.cleanPath(materi.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Materi file = new Materi(fileName, materi.getContentType(), materi.getBytes());
			em.persist(file);
			return file;
			
		} catch (IOException e) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
		}
	}

	@Override
	public Materi getFile(String fileId) throws Exception {
//		Query q = em.createQuery("from Materi where id = :idParam").setParameter("idParam", fileId);
//		return (Materi) q.getSingleResult();
		return materiRepo.findById(fileId).orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
}
