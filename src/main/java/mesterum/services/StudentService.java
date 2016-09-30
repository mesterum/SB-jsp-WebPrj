package mesterum.services;

import java.util.Iterator;
import java.util.function.Consumer;

//import javax.persistence.EntityManagerFactory;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import mesterum.dao.StudentRepository;
import mesterum.models.Student;

@org.springframework.stereotype.Service
public class StudentService {
	@Autowired StudentRepository dao;
	/*private SessionFactory sessionFactory;
	@Autowired
	public StudentService(EntityManagerFactory factory) {
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	    if(this.sessionFactory == null){
	        throw new NullPointerException("factory is not a hibernate factory");
	      }
	}*/
	public Iterator<Student> iselect(){
		return dao.readByDelOrderById('!').iterator();
	}
	@Transactional(readOnly=true)
	public Student selectById(short id){
		return selectById(id, e->{});
	}
	@Transactional(readOnly=true)
	public Student selectById(short id, Consumer<Student> consm){
		Student entity = dao.findOne(new Short(id));
        consm.accept(entity);
	    return entity;
	}
	@Modifying @Transactional
	public void delete(short id) {
		//dao.delete(new StudentPK(id));
		Student entity = dao.findOne(new Short(id));
		if(entity==null)return;
		entity.setDel(true);
		dao.save(entity);
	}
	@Modifying @Transactional
	public void update(Student stud) {
		dao.save(stud);
	}
	@Modifying @Transactional
	public void insert(Student stud) {
		dao.save(stud);
	}
}
