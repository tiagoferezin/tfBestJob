package br.com.bestjob.controller;

import javax.inject.Inject;

import br.com.bestjob.model.repository.MunicipioRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class MunicipioController {

	@Inject
	private Result result;
	
	@Inject
	private MunicipioRepository municipioRepository;

	@Get({"/municipio/listar", "/municipio/listar/"})
	public void listar() {
		result.use(Results.json())
        .withoutRoot()
        .from(municipioRepository.list())
        .serialize();
	}
	
}
