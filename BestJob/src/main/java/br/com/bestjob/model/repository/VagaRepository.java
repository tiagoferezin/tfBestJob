/**
 * 
 */
package br.com.bestjob.model.repository;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.bestjob.model.entity.Municipio;
import br.com.bestjob.model.entity.Vaga;

/**
 * @author Tiago Ferezin
 *
 */

@RequestScoped
public class VagaRepository {

	@Inject
	private Session session;

	private Criteria createCriteria() {
		return session.createCriteria(Vaga.class);
	}

	@SuppressWarnings("unchecked")
	public List<Vaga> list() {
		return createCriteria().list();
	}

}
