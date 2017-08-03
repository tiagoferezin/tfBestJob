/**
 * 
 */
package br.com.bestjob.utils;

import java.text.Normalizer;

/**
 * @author Tiago Ferezin
 *
 */
public class Normalizacao {

	public static String removerAcentos(String texto) {
		return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	public static String removerEspacos(String texto) {
		return texto.replaceAll(" ", "");
	}

	public static String getTextoPesquisa(String textoPesquisa) {
		String result = "";
		
		textoPesquisa = textoPesquisa.toUpperCase().trim();
		textoPesquisa= removerAcentos(textoPesquisa);
		textoPesquisa=removerEspacos(textoPesquisa);
		result = textoPesquisa.toUpperCase().trim();

		return result;
	}

}
