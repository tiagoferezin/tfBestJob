package br.com.bestjob.test.dao;

public interface ITestDAO {

	public Object getObjectTest();

	// Add annotation:
	// @BeforeTest
	public void beforeTest() throws Exception;

	// Add annotation:
	// @AfterTest
	public void afterTest();

	// Add annotation:
	// @Test(priority=10)
	public void criacao();

	// Add annotation:
	// @Test(priority=20)
	public void consulta();

	// Add annotation:
	// @Test(priority=30)
	public void atualizacao();

	// Add annotation:
	// @Test(priority=40)
	public void delecao();

}
