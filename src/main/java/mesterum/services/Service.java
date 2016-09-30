/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.services;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import mesterum.dao.Dao;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 * @param <E>
 */
//@org.springframework.stereotype.Service
public class Service<E> {
	@Autowired
    protected Dao<E> dao;
    //protected Class<E> type;

    public Service(Class<E> type) {
//        dao=Dao.getDao(type);
    //    this.type = type;
    }

    public Service() {
    }
    
    public void insert(E entity) {
        dao.inSessionWTransaction(()->{dao.save(entity);return null;});
    }

    public void update(E entity) {
        dao.inSessionWTransaction(()->{dao.update(entity);return null;});
    }

    public E selectById(Serializable id) {
        return selectById(id, e->{});
    }

    public E selectById(Serializable id, Consumer<E> consm) {
        return dao.inSession(()->{
            E entity = dao.findById(id);
            consm.accept(entity);
            return entity;
        });
    }

    public void delete(Serializable id) {
    dao.inSessionWTransaction(()->{
        E entity = dao.findById(id);
        dao.delete(entity);return null;});
    }

    public List<E> select() {
        return dao.new Type<List>().inSession(dao::findAll);
    }

//    public Stream<E> sselect(){
//        return dao.findAllAsStream();
//    }

    public Iterator<E> iselect(){
//        Dao<E>.Type<Iterator> r;
//        r = dao.new Type<Iterator>(Iterator.class);
        return dao.findAllAsStream().iterator();
    }
}
