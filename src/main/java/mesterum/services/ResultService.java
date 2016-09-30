package mesterum.services;

import java.util.Iterator;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import mesterum.dao.ResultRepository;
import mesterum.models.Result;
import mesterum.models.ResultPK;

@org.springframework.stereotype.Service
public class ResultService {

	@Autowired ResultRepository dao;
	public Iterator<Result> iselect(){
		return dao.readAll().iterator();
	}
	@Transactional(readOnly=true)
	public Result selectById(ResultPK id){
		return selectById(id, e->{});
	}
	@Transactional(readOnly=true)
	public Result selectById(ResultPK id, Consumer<Result> consm){
		Result entity = dao.findOne(id);
        consm.accept(entity);
	    return entity;
	}
	@Modifying @Transactional
	public void delete(ResultPK id) {
		dao.delete(id);
	}
	@Modifying @Transactional
	public void update(Result entity) {
		dao.save(entity);
	}
	@Modifying @Transactional
	public void insert(Result entity) {
		dao.save(entity);
	}
}
