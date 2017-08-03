package br.com.bestjob.test.utils;

import org.testng.annotations.Test;

import br.com.bestjob.utils.Normalizacao;

public class NormalizacaoTestNG {
	
	@Test
	public void geral() {
		
		String texto = "LÚciAâÂna dE  Páuì";
		System.out.println(texto);
		
		String textoCorrigido = "";
		
		textoCorrigido= Normalizacao.getTextoPesquisa(texto);
		
		System.out.println(textoCorrigido);
		
	}

}
