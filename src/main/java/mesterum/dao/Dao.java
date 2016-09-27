/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.persistence.Id;
import javax.persistence.IdClass;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mesterum.util.HibernateUtil;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 * @param <E>
 */
@Component
public class Dao<E> {
    protected Session currentSession;
    protected Transaction currentTransaction;
    protected Class<E> type;
    protected String idFields="";
    protected String findAllQuery;
    static private Map<Class, Dao> daoMap = new HashMap<>();

    protected Dao(Class<E> type) {
        this.type = type;
        setIdFields();
    }

    public Dao() {
		super();
	}

	public static Dao getDao(Class type) {
        Dao dao = daoMap.get(type);
        if(dao==null)daoMap.put(type, dao = new Dao(type));
        return dao;
    }

    private void setIdFields() {
        Class c = type;
        Annotation a = c.getAnnotation(IdClass.class);
        if(a!=null) c=((IdClass)a).value();
        for(Field f:c.getDeclaredFields())
          if(f.getAnnotation(Id.class)!=null)
            idFields+=(idFields.isEmpty()?" ":",")+f.getName();
        System.out.println(idFields);
    }

    public E inSession(Supplier<E> sup){
        E rez = null;
	try{currentSession = hu.getSessionFactory().openSession();
            rez = sup.get();
        }finally {
          if (currentSession != null)
            currentSession.close();
        }
        return rez;
    }

    public class Type<T>{
//        private final Class<T> type;

        public Type() {
//            this.type = type;
        }
        
      public T inSession(Supplier<T> sup){
	currentSession = hu.getSessionFactory().openSession();
            T rez = sup.get();
        currentSession.close();
        return rez;
      }
    }
    
//    public List<E> inSessionL(Supplier<List<E>> sup){
//	currentSession = HibernateUtil.getSessionFactory().openSession();
//            List<E> rez = sup.get();
//        currentSession.close();
//        return rez;
//    }

    public Stream<E> inSessionI(Supplier<Iterator<E>> sup){
	currentSession = hu.getSessionFactory().openSession();
        Stream<E> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
            sup.get(), Spliterator.ORDERED), false).onClose(currentSession::close);
        return stream;
    }

    public E inSessionWTransaction(Supplier<E> sup){
	return inSession(()->{
          E rez = null;
          try{currentTransaction = currentSession.beginTransaction();
                rez = sup.get();
            currentTransaction.commit();
          } catch (RuntimeException e) {
            if ( currentTransaction != null && currentTransaction.getStatus() == 
            		TransactionStatus.ACTIVE) currentTransaction.rollback();
            throw e;
          } return rez;
        });
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void save(E entity) {
        getCurrentSession().save(entity);
    }

    public void update(E entity) {
        getCurrentSession().update(entity);
    }

    public E findById(Serializable id) {
        return (E) getCurrentSession().get(type, id);
    }

    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

    public String getFindAllQuery() {
        if(findAllQuery==null)
            findAllQuery="from "+type.getSimpleName()+
                (idFields.isEmpty()?"":" order by"+idFields);
        return findAllQuery;
    }

    public List<E> findAll() {
        return (List<E>) getCurrentSession().createQuery(getFindAllQuery()).list();
    }

    public Iterator<E> findAllAsIterator() {
        if(getCurrentSession()==null)return null;
        return getCurrentSession().createQuery(getFindAllQuery()).iterate();
    }
    @Autowired
    private HibernateUtil hu;
    public Stream<E> findAllAsStream() {
        final StatelessSession statelessSession = hu.getSessionFactory().openStatelessSession();
        final ScrollableResults scrollableResults = statelessSession
            .createQuery(getFindAllQuery())
            .scroll(ScrollMode.FORWARD_ONLY);

        return StreamSupport.stream(new Spliterators.AbstractSpliterator<E>(
                Long.MAX_VALUE, Spliterator.ORDERED) {
                @Override
                public boolean tryAdvance(Consumer<? super E> action) {
                    if(!scrollableResults.next()) return false;
                    action.accept((E)scrollableResults.get(0));
                    return true;
                }
                @Override
                public void forEachRemaining(Consumer<? super E> action) {
                    while(scrollableResults.next()) action.accept((E)scrollableResults.get(0));
                }
        }, false).onClose(()->{
            scrollableResults.close();
            statelessSession.close();
        });
    }
}
