package br.com.bestjob.controller;

/**
 * @author Tiago Ferezin
 *
 */

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

@Controller
public class HomeController {

	@Inject
	private Result result;

	@Get({ "/", "/home" })
	public void home() {

	}

}
