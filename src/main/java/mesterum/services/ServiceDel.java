/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.services;

import org.springframework.beans.factory.annotation.Autowired;

import mesterum.dao.Dao;
import mesterum.dao.DaoDel;
import mesterum.models.Del;
import mesterum.models.Student;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 * @param <E>
 */
public class ServiceDel<E extends Del> extends Service<E> {
	@Autowired
    protected DaoDel<E> dao;
    
    public ServiceDel() {
        super();
        //dao=DaoDel.getDao(type);
    }
}

