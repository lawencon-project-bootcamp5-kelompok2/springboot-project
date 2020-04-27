package com.lawencon.app.springbootproject.dao.impl.hibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.lawencon.app.springbootproject.dao.LoginDao;
import com.lawencon.app.springbootproject.dao.RoleDao;
import com.lawencon.app.springbootproject.dao.StudentDao;
import com.lawencon.app.springbootproject.model.Login;
import com.lawencon.app.springbootproject.model.Role;
import com.lawencon.app.springbootproject.model.Student;
import com.lawencon.app.springbootproject.payload.request.SignupRequest;

@Repository
public class StudentDaoImpl extends BaseHibernate implements StudentDao {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private LoginDao loginDao;
	
	@Override
	public void createStudent(Student student) throws Exception {
		em.persist(student);
	}

	@Override
	public void updateStudent(Student student) throws Exception {
		Student s = findById(student.getIdStudent());
		s.setNamaStudent(student.getNamaStudent());
		s.setKelas(student.getKelas());
		em.merge(s);
	}

	@Override
	public void deleteStudent(String idStudent) throws Exception {
		em.remove(findById(idStudent));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> findAll() throws Exception {
		Query q = em.createQuery(" FROM Student");
		return q.getResultList();
	}

	@Override
	public Student findById(String idStudent) throws Exception {
		Query q = em.createQuery(" FROM Student WHERE idStudent =:idParam");
		q.setParameter("idParam", idStudent);
		return (Student) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakReportStudent(String id) throws Exception {
		Query q = em.createNativeQuery("select \r\n" + "	sc.nama_subcourse , j.nilai , s.nama_student \r\n"
				+ "from \r\n" + "	student s \r\n" + "	join jawaban j on j.id_student = s.id_student \r\n"
				+ "	join course c on c.id_course = s.id_course \r\n"
				+ "	join subcourse sc on sc.id_course = c.id_course \r\n" + "where \r\n"
				+ "	s.id_student = :idParam").setParameter("idParam", id);
		return bMapperHibernate(q.getResultList(), "namaSubcourse", "nilai", "namaStudent");
	}

	@Override
	public Student validStudent(Student student) throws Exception {
		Query q = em.createQuery("from Student where npm = :npmParam and namaStudent = :namaParam");
		q.setParameter("npmParam", student.getNpm());
		q.setParameter("namaParam", student.getNamaStudent());
		return (Student) q.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> cetakReportStudent(String idStudent, String idCourse) throws Exception {
		Query q = em
				.createNativeQuery("select distinct " + "	s.nama_student , j.nilai, sc.nama_subcourse, c.nama_course "
						+ "	from " + "	student s " + "	join jawaban j on j.id_student = s.id_student "
						+ "	join test t on t.id_test = j.id_test "
						+ "	join subcourse sc on sc.id_subcourse = t.id_subcourse "
						+ "	join course c on c.id_course = sc.id_course "
						+ "	where s.id_student = :studentParam and c.id_course = :courseParam")
				.setParameter("studentParam", idStudent).setParameter("courseParam", idCourse);
		return bMapperHibernate(q.getResultList(), "namaStudent", "nilai", "namaSubcourse", "namaCourse");
	}

	@Override
	public String getNamaStudent(String idStudent) throws Exception {
		Query q = em.createNativeQuery("SELECT nama_student FROM student WHERE id_student = :idParam")
				.setParameter("idParam", idStudent);
		return (String) q.getSingleResult();
	}

	@Override
	public Student findByEmail(String email) {
		Query q = em.createQuery(" FROM Student WHERE email =:emailParam");
		q.setParameter("emailParam", email);
		return (Student) q.getSingleResult();
	}

	@Override
	public Boolean existsByEmail(String email) {
		Query q = em.createQuery("SELECT COUNT(*) FROM Student WHERE email =:emailParam");
		q.setParameter("emailParam", email);
		Long result = (Long) q.getSingleResult();
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public void createStudents(SignupRequest signUpRequest) throws Exception {
		Login user = new Login(signUpRequest.getNama(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
		Role userRole = roleDao.findRoleStudent();
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		Random random = new Random();
		Student s = new Student();
		s.setEmail(user.getEmail());
		s.setNamaStudent(user.getNama());
		s.setPassword(user.getPassword());
		s.setRole(userRole.getName().toString());
		s.setNpm("01"+formatter.format(LocalDate.now()).toString()+random.nextInt(900));
		em.persist(s);
		user.setRoles(roles);
		loginDao.insertUser(user);
	}
}
