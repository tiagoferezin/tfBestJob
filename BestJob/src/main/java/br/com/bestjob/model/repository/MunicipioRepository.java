package br.com.bestjob.model.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.bestjob.model.entity.Municipio;

@RequestScoped
public class MunicipioRepository {

	@Inject
	private Session session;

	private Criteria createCriteria() {
		return session.createCriteria(Municipio.class);
	}

	@SuppressWarnings("unchecked")
	public List<Municipio> list() {
		return createCriteria().list();
	}

}
