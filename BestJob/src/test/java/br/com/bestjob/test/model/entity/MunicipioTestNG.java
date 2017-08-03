/**
 * 
 */
package br.com.bestjob.test.model.entity;

import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.com.bestjob.model.entity.Municipio;
import br.com.bestjob.model.enumerado.EEstado;
import br.com.bestjob.test.dao.ATestDAO;

/**
 * @author Tiago Ferezin
 *
 */
public class MunicipioTestNG extends ATestDAO<Municipio> {

	private Municipio municipio;

	@BeforeTest
	@Override
	public void beforeTest() throws Exception {
		// TODO Auto-generated method stub
		super.beforeTest();
	}

	@AfterTest
	@Override
	public void afterTest() {
		// TODO Auto-generated method stub
		super.afterTest();
	}

	@Test(priority = 10)
	@Override
	public void criacao() {
		// TODO Auto-generated method stub
		String erro = "";

		try {

			municipio = new Municipio();
			Calendar cal = Calendar.getInstance();
			municipio.setEstado(EEstado.SP);
			municipio.setNome("Sertãozinho".toUpperCase());
			genericDAOFactory.create(municipio, this.entityManager);

			Assert.assertNotNull(municipio.getIdMunicipio());
			Assert.assertNotEquals(municipio.getIdMunicipio(), 0L);

		} catch (Exception e) {
			// TODO: handle exception
			erro = e.getMessage();
			e.printStackTrace();
		}

		Assert.assertEquals(erro, "");

	}

	@Test(priority = 20)
	@Override
	public void consulta() {
		// TODO Auto-generated method stub
		String erro = "";
		try {

			Long id = municipio.getIdMunicipio();

			Municipio consulta = (Municipio) genericDAOFactory.readPorId(municipio, this.entityManager, id);
			Assert.assertNotNull(consulta);

		} catch (Exception e) {
			// TODO: handle exception
			erro = e.getMessage();
			e.printStackTrace();
		}
		Assert.assertEquals(erro, "");
	}

	@Test(priority = 30)
	@Override
	public void atualizacao() {
		// TODO Auto-generated method stub

		String erro = "";

		try {

			String desc = "Mudou";
			municipio.setNome(desc);
			genericDAOFactory.update(municipio, this.entityManager);
			Assert.assertNotNull(municipio);
			Assert.assertEquals(municipio.getNome(), desc);

		} catch (Exception e) {
			// TODO: handle exception
			erro = e.getMessage();
			e.printStackTrace();
		}

		Assert.assertEquals(erro, "");
	}

	@Test(priority = 40)
	@Override
	public void delecao() {
		// TODO Auto-generated method stub

		String erro = "";

		try {
			genericDAOFactory.delete(municipio, this.entityManager);
			Assert.assertNotNull(municipio.getDataDesativacao());
		} catch (Exception e) {
			// TODO: handle exception
			erro = e.getMessage();
			e.printStackTrace();
		}
		Assert.assertEquals(erro, "");
	}

}
