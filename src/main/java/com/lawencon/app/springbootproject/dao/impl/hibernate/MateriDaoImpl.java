package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.app.springbootproject.dao.MateriDao;
import com.lawencon.app.springbootproject.exception.FileStorageException;
import com.lawencon.app.springbootproject.exception.ELearningFileNotFoundException;
import com.lawencon.app.springbootproject.model.Materi;

@Repository
public class MateriDaoImpl extends BaseHibernate implements MateriDao{
	
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
		try {
			Query q = em.createQuery("from Materi where id = :idParam").setParameter("idParam", fileId);
			return (Materi) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			new ELearningFileNotFoundException("File not found with id : "+fileId);
		}
		return null;
	}

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Materi");
		return q.getResultList();
	}
}
