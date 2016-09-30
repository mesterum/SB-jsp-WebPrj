package mesterum.services;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import mesterum.dao.CourseRepository;
import mesterum.models.Course;

@org.springframework.stereotype.Service
public class CourseService {

	@Autowired CourseRepository dao;
	public Iterator<Course> iselect(){
		return dao.readByDelOrderById('!').iterator();
	}
	@Transactional(readOnly=true)
	public Course selectById(String id){
		return selectById(id, e->{});
	}
	@Transactional(readOnly=true)
	public Course selectById(String id, Consumer<Course> consm){
		Course entity = dao.findOne(id);
        consm.accept(entity);
	    return entity;
	}
	@Modifying @Transactional
	public void delete(String id) {
		Course entity = dao.findOne(id);
		if(entity==null)return;
		entity.setDel(true);
		dao.save(entity);
	}
	@Modifying @Transactional
	public void update(Course entity) {
		dao.save(entity);
	}
	@Modifying @Transactional
	public void insert(Course entity) {
		dao.save(entity);
	}
	public List<Course> select() {
		return dao.readByDelOrderById('!').collect(Collectors.toList());
	}
}
