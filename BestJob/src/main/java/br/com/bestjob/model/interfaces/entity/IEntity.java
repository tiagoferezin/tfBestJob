/**
 * 
 */
package br.com.bestjob.model.interfaces.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Windows 7
 *
 */
public interface IEntity extends Serializable {

	public Long getId();

	public void setId(Long id);

	public boolean isEmptyId();

	public boolean isDeleted();

	public Calendar getDataDesativacao();

	public void setDataDesativacao(Calendar dataDesativacao);

}
