/**
 * 
 */
package br.com.bestjob.dao.factory;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import br.com.bestjob.dao.GenericDAO;
import br.com.bestjob.model.entity.AEntity;

/**
 * @author Tiago Ferezin
 *
 */
public class GenericDAOFactory {

	public void create(AEntity entity, EntityManager entityManager) throws ConstraintViolationException, Exception {

		GenericDAO result = new GenericDAO(entity, entityManager);
		result.create();
	}
	
	public void readAll(AEntity entity, EntityManager entityManager, Boolean ativos) throws Exception {
		
		GenericDAO result = new GenericDAO(entity, entityManager);
		result.readAll(entity, entityManager, ativos);
		
	}

}
