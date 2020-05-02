package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.KelasDao;
import com.lawencon.app.springbootproject.model.Kelas;

@Repository
public class KelasDaoImpl extends BaseHibernate implements KelasDao {

	@Override
	public List<?> findAll() throws Exception {
		Query q = em.createQuery("from Kelas");
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<?> findAvailableClass(String idStudent) throws Exception {
		Query q = em.createNativeQuery("select k.id_kelas, k.kode_kelas, c.nama_course, t.nama_trainer, k.open_kelas " +
				" from kelas k join course c on k.id_course = c.id_course join trainer t on t.id_trainer = c.id_trainer" + 
				" where k.id_kelas not in " + 
				"	(select kelas_id_kelas from student_kelas " + 
				"		where student_id_student = :idParam)");
		q.setParameter("idParam", idStudent);
		return bMapperHibernate(q.getResultList(), "idKelas", "kodeKelas", "namaCourse", "namaTrainer", "openKelas");
	}

	@Override
	public Kelas findById(String idKelas) throws Exception {
		Query q = em.createQuery("from Kelas where idKelas = :idParam");
		q.setParameter("idParam", idKelas);
		return (Kelas) q.getSingleResult();
	}

	@Override
	public void insert(Kelas kelas) throws Exception {
		em.persist(kelas);
	}

	@Override
	public void update(Kelas kelas) throws Exception {
		Kelas k = findById(kelas.getIdKelas());
		k.setKodeKelas(kelas.getKodeKelas());
		k.setCourse(kelas.getCourse());
		k.setOpenKelas(kelas.getOpenKelas());
		em.merge(k);
	}

	@Override
	public void delete(String idKelas) throws Exception {
		em.remove(findById(idKelas));
	}

	@Override
	public Kelas validKelas(Kelas kelas) throws Exception {
		Query q = em.createQuery("from Kelas where openKelas = :openParam");
		q.setParameter("openParam", kelas.getOpenKelas());
		return (Kelas) q.getSingleResult();
	}

	@Override
	public String getNamaKelas(String id) throws Exception {
		Query q = em.createNativeQuery("SELECT kode_kelas FROM kelas WHERE id_kelas = :idParam")
				.setParameter("idParam", id);
		return (String) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakKelas() throws Exception {
		Query q = em.createNativeQuery("select k.kode_kelas, c.nama_course, t.nama_trainer " + 
				"from kelas k join course c on k.id_course = c.id_course join trainer t on t.id_trainer = c.id_trainer ");

		return bMapperHibernate(q.getResultList(), "kodeKelas", "namaCourse", "namaTrainer");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> getByTrainer(String id) throws Exception {
		Query q = em.createNativeQuery("select c.id_course, k.kode_kelas , c.nama_course , k.open_kelas from kelas k join course c on k.id_course = c.id_course join trainer t on t.id_trainer = c.id_trainer " + 
				"where t.id_trainer = :idParam");
		q.setParameter("idParam", id);
		return bMapperHibernate(q.getResultList(), "idCourse", "kodeKelas", "namaCourse", "openKelas");
	}
}
