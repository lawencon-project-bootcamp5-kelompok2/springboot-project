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
	public Forum findById(String idForum) throws Exception {
		Query q = em.createQuery("from Forum where idForum = :idParam").
				setParameter("idParam", idForum);
		return (Forum) q.getSingleResult();
	}

	@Override
	public void createForum(Forum forum) throws Exception {
		em.persist(forum);
	}

	@Override
	public Forum update(Forum forum) throws Exception {
		Forum forums = findById(forum.getIdForum());
		forums.setSubjek(forum.getSubjek());
		forums.setDeskripsi(forum.getDeskripsi());
		em.merge(forums);
		return forums;
	}

	@Override
	public void delete(String idForum) throws Exception {
		em.remove(findById(idForum));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> findByIdPertemuan(String idPertemuan) throws Exception {
		Query q = em.createNativeQuery("select "
				+ "f.id_forum, f.subjek, f.email_sender, f.deskripsi "
				+ "from forum f join "
				+ "pertemuan p on f.id_pertemuan = p.id_pertemuan where f.id_pertemuan = :idParam");
		q.setParameter("idParam", idPertemuan);
		return bMapperHibernate(q.getResultList(), "idForum", "subjek", "emailSender", "deskripsi");
	}

}
