package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.TrainerDao;
import com.lawencon.app.springbootproject.model.Trainer;

@Repository
public class TrainerDaoImpl extends BaseHibernate implements TrainerDao {

	@Override
	public void createTrainer(Trainer trainer) throws Exception{
		em.persist(trainer);
	}

	@Override
	public void updateTrainer(Trainer trainer) throws Exception{
		Trainer t = findById(trainer.getIdTrainer());
		t.setNamaTrainer(trainer.getNamaTrainer());
		t.setEmail(trainer.getEmail());
		t.setPassword(trainer.getPassword());
		t.setRole(trainer.getRole());
		em.merge(t);
	}

	@Override
	public void deleteTrainer(String idTrainer) throws Exception{
		em.remove(findById(idTrainer));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Trainer> findAll() throws Exception{
		Query q = em.createQuery(" FROM Trainer");
		return q.getResultList();
	}

	@Override
	public Trainer findById(String idTrainer) throws Exception{
		Query q = em.createQuery(" FROM Trainer WHERE idTrainer =:idParam");
		q.setParameter("idParam", idTrainer);
		return (Trainer) q.getSingleResult();
	}

	@Override
	public Trainer validTrainer(Trainer trainer) throws Exception {
		Query q = em.createQuery("from Trainer where email = :emailParam and namaTrainer = :namaParam");
		q.setParameter("emailParam", trainer.getEmail());
		q.setParameter("namaParam", trainer.getNamaTrainer());
		return (Trainer) q.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakReportTrainer(String idTrainer, String idTest, String idSubcourse) throws Exception {
		Query q = em.createNativeQuery("select distinct " + 
				"	s.nama_student, a.status, j.nilai, sc.nama_subcourse, t.nama_trainer \r\n" + 
				"from \r\n" + 
				"	student s \r\n" + 
				"	join absensi a on s.id_student = a.id_student " + 
				"	join subcourse sc on sc.id_subcourse = a.id_subcourse " +	
				"	join jawaban j on j.id_student = s.id_student \r\n" + 
				"	join course c on c.id_course = sc.id_course \r\n" + 
				"	join trainer t on t.id_trainer = c.id_trainer " + 
				"	where t.id_trainer = :idTrainer and j.id_test = :idTest and sc.id_subcourse = :idSubcourse ")
				.setParameter("idTrainer", idTrainer).setParameter("idTest", idTest).setParameter("idSubcourse", idSubcourse);
		return bMapperHibernate(q.getResultList(), "namaStudent", "status", "nilai", "namaSubcourse", "namaTrainer");
	}

	@Override
	public Trainer getNamaTrainer(String idTrainer) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_trainer FROM trainer WHERE id_trainer = :idParam").setParameter("idParam", idTrainer);
		return (Trainer) q.getSingleResult();
	}
	
}
