package br.com.bestjob.test.dao;

import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import br.com.bestjob.dao.factory.GenericDAOFactory;

public abstract class ATestDAO<AEntity> implements ITestDAO {

	protected String userNameUnitTest = "test@test.com";

	protected AEntity objectTest;

	protected GenericDAOFactory genericDAOFactory;
	protected EntityManager entityManager;
	protected EntityManagerFactory entityManagerFactory;
	protected SimpleDateFormat sdf;

	public Class<AEntity> getClassT() throws Exception {
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();

		return (Class<AEntity>) superclass.getActualTypeArguments()[0];
	}

	protected AEntity getInstanceT() throws Exception {

		Class<AEntity> classT = getClassT();

		return classT.newInstance();
	}

	@Override
	public AEntity getObjectTest() {
		return this.objectTest;
	}

	@BeforeTest
	@Override
	public void beforeTest() throws Exception {
		entityManagerFactory = Persistence.createEntityManagerFactory("postgreSQL");
		entityManager = entityManagerFactory.createEntityManager();

		objectTest = getInstanceT();
		genericDAOFactory = new GenericDAOFactory();
	}

	@Override
	@AfterTest
	public void afterTest() {
		if (this.entityManager.isOpen()) {
			this.entityManager.close();
		}
		if (this.entityManagerFactory.isOpen()) {
			this.entityManagerFactory.close();
		}

	}

}
