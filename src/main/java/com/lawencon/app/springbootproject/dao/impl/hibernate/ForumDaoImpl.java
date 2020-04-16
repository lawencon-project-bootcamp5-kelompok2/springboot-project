package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.ForumDao;
import com.lawencon.app.springbootproject.model.Forum;

@Repository
public class ForumDaoImpl extends BaseHibernate implements ForumDao{

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Forum");
		return q.getResultList();
	}

	@Override
	public Forum findById(Forum forum) throws Exception {
		Query q = em.createQuery("from Forum where idForum = :idParam").
				setParameter("idParam", forum.getIdForum());
		return (Forum) q.getSingleResult();
	}

	@Override
	public void createForum(Forum forum) throws Exception {
		em.persist(forum);
	}

	@Override
	public Forum update(Forum forum) throws Exception {
		Forum forums = findById(forum);
		forums.setSubjek(forum.getSubjek());
		forums.setDeskripsi(forum.getDeskripsi());
		em.merge(forums);
		return forums;
	}

	@Override
	public void delete(Forum forum) throws Exception {
		em.remove(findById(forum));
	}

}