/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.dao;

import java.util.List;

import mesterum.models.Del;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 * @param <E>
 */
public class DaoDel<E extends Del> extends Dao<E>{
    
    protected DaoDel(Class<E> type) {
        super(type);
    }
    
    @Override
    public void delete(E entity) {
        entity.setDel(true);
        getCurrentSession().update(entity);
    }

    @Override
    public String getFindAllQuery() {
        if(findAllQuery==null)
            findAllQuery="from "+type.getSimpleName()+" where del=' '"+
                (idFields.isEmpty()?"":" order by"+idFields);
        return findAllQuery;
    }
}
