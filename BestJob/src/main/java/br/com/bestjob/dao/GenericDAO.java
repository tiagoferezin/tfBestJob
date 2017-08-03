/**
 * 
 */
package br.com.bestjob.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;

import br.com.bestjob.dao.factory.GenericDAOFactory;
import br.com.bestjob.model.entity.AEntity;

/**
 * @author Tiago Ferezin
 *
 */
public class GenericDAO {

	final static Logger logger = Logger.getLogger(GenericDAO.class);

	@Inject
	private EntityManager entityManager;

	protected AEntity entity;

	protected GenericDAOFactory genericDAOFactory;

	@SuppressWarnings("unchecked")
	public GenericDAO(AEntity entity, EntityManager entityManager) {

		genericDAOFactory = new GenericDAOFactory();

		setEntity(entity);
		setEntityManager(entityManager);

	}

	public void create() throws Exception, ConstraintViolationException {

		boolean closeTransaction = false;

		try {

			beforePersistence();
			if (!entityManager.getTransaction().isActive()) {
				logger.debug("\n*** Starting transaction \n");
				entityManager.getTransaction().begin();
				closeTransaction = true;
			}

			entityManager.persist(this.entity);

			if (entityManager.getTransaction().isActive()) {
				afterPersistence();
				if (closeTransaction) {
					logger.debug("\n*** Commiting transaction \n");
					entityManager.getTransaction().commit();
				}
			}
		} catch (ConstraintViolationException e) {
			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}
			}

			e.printStackTrace();

			throw e;

		} catch (Exception e) {

			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}
			}

			e.printStackTrace();

			throw e;

		} finally {
			// if (closeTransaction) {
			// logger.debug("\n*** Closing EM\n");
			// entityManager.close();
			// }
		}

	}

	/**
	 * @return the entity
	 */
	public AEntity getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(AEntity entity) {
		this.entity = entity;
	}

	EntityManager getEntityManager() {
		return entityManager;
	}

	void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	void beforePersistence() throws ConstraintViolationException, Exception {
	}

	void afterPersistence() throws ConstraintViolationException, Exception {
	}

	Class<AEntity> getClassT() throws Exception {
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();

		return (Class<AEntity>) superclass.getActualTypeArguments()[0];
	}

	protected AEntity getInstanceT() throws Exception {

		Class<AEntity> classT = getClassT();

		return classT.newInstance();
	}

	private void initializeEntityManager() throws Exception {
		if ((entityManager == null) || (!entityManager.isOpen())) {
			throw new Exception(
					"O entityManager do acesso a dados da " + entity.getClass() + " está fechado ou é nulo.");
		}
	}

	public void update() throws Exception, ConstraintViolationException {
		initializeEntityManager();

		boolean closeTransaction = false;

		try {
			// T encontrada = (T) manager.find(entity.getClass(),
			// entity.getId());

			beforePersistence();
			if (!entityManager.getTransaction().isActive()) {
				logger.debug("\n*** Starting transaction \n");
				entityManager.getTransaction().begin();
				closeTransaction = true;

			}

			entityManager.merge(this.entity);
			if (entityManager.getTransaction().isActive()) {
				afterPersistence();
				if (closeTransaction) {
					logger.debug("\n*** Commiting transaction \n");
					entityManager.getTransaction().commit();
				}
			}
		} catch (ConstraintViolationException e) {
			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}

			}

			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}

			}
			throw e;
		} finally {
			// if (closeTransaction) {
			// logger.debug("\n*** Closing EM\n");
			// if ((entityManager != null) && (entityManager.isOpen())) {
			// entityManager.close();
			// }
			// }
		}
	}

	public void delete() throws Exception {

		initializeEntityManager();

		boolean closeTransaction = false;
		try {

			beforePersistence();
			// T encontrada = (T) manager.find(entity.getClass(),
			// entity.getId());
			if (!entityManager.getTransaction().isActive()) {
				logger.debug("\n*** Starting transaction \n");
				entityManager.getTransaction().begin();
				closeTransaction = true;

			}
			if (entity instanceof AEntity) {
				entity.setDataDesativacao(Calendar.getInstance());
				entityManager.merge(this.entity);
			} else {
				entityManager.remove(entityManager.getReference(entity.getClass(), entity.getId()));
			}

			if (entityManager.getTransaction().isActive()) {
				afterPersistence();
				if (closeTransaction) {
					logger.debug("\n*** Commiting transaction \n");
					entityManager.getTransaction().commit();
				}

				entity.setId(0L);
			}
		} catch (Exception e) {

			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}
			}
			throw e;

		} finally {
			// if (closeTransaction) {
			// logger.debug("\n*** Closing EM\n");
			// entityManager.close();
			// }
		}

	}

	public Long count(AEntity entity, String userName, EntityManager entityManager) throws Exception {
		String hQl = "select count(*) from " + entity.getClass().getSimpleName() + " t";

		return readAtt(entity, entityManager, hQl);
	}

	public Long readAtt(AEntity entity, EntityManager entityManager, String hQl) throws Exception {
		Long result = 0L;

		initializeEntityManager();

		Query query = entityManager.createQuery(hQl);

		result = (Long) query.getSingleResult();

		return result;

	}

	public List<AEntity> readAll(AEntity entity, EntityManager entityManager, Boolean ativos) throws Exception {

		List<AEntity> result = new ArrayList<AEntity>();

		initializeEntityManager();
		boolean closeTransaction = false;

		try {
			if (!entityManager.getTransaction().isActive()) {
				logger.debug("\n*** Starting transaction \n");
				entityManager.getTransaction().begin();
				closeTransaction = true;
			}

			String hQl = "from " + entity.getClass().getSimpleName() + " t ";

			if (ativos) {
				hQl += " where (dataDesativacao is null)";
			}

			logger.debug("HQL: ");
			logger.debug(hQl);

			Query query = entityManager.createQuery(hQl);
			result = query.getResultList();

			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Commiting transaction \n");
					entityManager.getTransaction().commit();
				}
			}

		} catch (Exception e) {

			if (entityManager.getTransaction().isActive()) {
				if (closeTransaction) {
					logger.debug("\n*** Rollback transaction\n");
					entityManager.getTransaction().rollback();
				}
			}

			throw e;

		}

		return result;

	}

}
