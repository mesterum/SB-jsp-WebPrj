/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesterum.models;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Mihai Manole <mihai.manole77@gmail.com>
 */
@MappedSuperclass
public class Del implements Serializable {
	@javax.persistence.Column
	protected char del='!';
    
    public boolean isDel() {
        return del>'!';
    }

    public void setDel(boolean del) {
        this.del = del?'"':'!';
    }

}
