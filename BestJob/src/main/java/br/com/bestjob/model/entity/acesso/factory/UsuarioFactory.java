/**
 * 
 */
package br.com.bestjob.model.entity.acesso.factory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.bestjob.model.entity.acesso.Usuario;

/**
 * @author Tiago Ferezin
 *
 */
public class UsuarioFactory {

	public UsuarioFactory() {

	}

	public Boolean chkSenha(Usuario usuario, String senhaDescriptografada)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Boolean result = false;

		if ((senhaDescriptografada != null) && (!senhaDescriptografada.isEmpty()) && (usuario.getSalt() != null)
				&& (!usuario.getSalt().isEmpty()) && (usuario.getSenha() != null) && (!usuario.getSenha().isEmpty())) {

			String pass = senhaDescriptografada;
			String nameSalt = usuario.getSalt();
			MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[] = algorithm.digest((pass + nameSalt).getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaDescriptografada = hexString.toString();
			// logger.debug(senhaDescriptografada);

			result = usuario.getSenha().equals(senhaDescriptografada);

		}

		return result;

	}

	public String chkAutenticar(Usuario usuario, String senhaDescriptografada) {
		String result = "";

		try {
			if (chkSenha(usuario, senhaDescriptografada)) {

				result = "";

			} else {
				result = "<strong>BESTJ-002:</strong> O usuário e/ou senha estão incorretos.";
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "<strong>BESTJ-002:</strong> O usuário e/ou senha estão incorretos.";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "<strong>BESTJ-002:<strong/> O usuário e/ou senha estão incorretos.";
		}

		return result;
	}

}
