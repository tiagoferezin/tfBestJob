/**
 * 
 */
package br.com.bestjob.model.entity;

import java.util.Calendar;

import br.com.bestjob.model.interfaces.entity.IEntity;

/**
 * @author Tiago Ferezin
 *
 */
public abstract class AEntity<T> implements IEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean isEmptyId() {
		return ((getId() == null) || (getId() == 0L));
	}	

}
