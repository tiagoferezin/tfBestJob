/**
 * 
 */
package br.com.bestjob.model.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.bestjob.model.enumerado.ETipoVaga;

/**
 * @author Tiago Ferezin
 *
 */

@Entity
public class Vaga extends AEntity<Vaga> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long idVaga;

	@Column(nullable = false)
	private String titulo;

	@Column(nullable = false)
	private Calendar dataCriacao;

	@Column(nullable = false)
	private String descricao;

	@Enumerated(EnumType.STRING)
	private ETipoVaga tipoVaga;

	@Column(nullable = false)
	private String emailRecrutador;

	private Calendar dataDesativacao;

	@Column(nullable = false)
	private Integer publicado;

	private String salario;

	public Vaga() {
		
	}

	/**
	 * @return the idVaga
	 */
	public Long getIdVaga() {
		return idVaga;
	}

	/**
	 * @param idVaga the idVaga to set
	 */
	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}

	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the dataCriacao
	 */
	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	/**
	 * @param dataCriacao the dataCriacao to set
	 */
	public void setDataCriacao(Calendar dataCriacao) {
		dataCriacao = Calendar.getInstance();
		this.dataCriacao = dataCriacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the tipoVaga
	 */
	public ETipoVaga getTipoVaga() {
		return tipoVaga;
	}

	/**
	 * @param tipoVaga the tipoVaga to set
	 */
	public void setTipoVaga(ETipoVaga tipoVaga) {
		this.tipoVaga = tipoVaga;
	}

	/**
	 * @return the emailRecrutador
	 */
	public String getEmailRecrutador() {
		return emailRecrutador;
	}

	/**
	 * @param emailRecrutador the emailRecrutador to set
	 */
	public void setEmailRecrutador(String emailRecrutador) {
		this.emailRecrutador = emailRecrutador;
	}

	/**
	 * @return the dataDesativacao
	 */
	public Calendar getDataDesativacao() {
		return dataDesativacao;
	}

	/**
	 * @param dataDesativacao the dataDesativacao to set
	 */
	public void setDataDesativacao(Calendar dataDesativacao) {
		this.dataDesativacao = dataDesativacao;
	}

	/**
	 * @return the publicado
	 */
	public Integer getPublicado() {
		return publicado;
	}

	/**
	 * @param publicado the publicado to set
	 */
	public void setPublicado(Integer publicado) {
		this.publicado = publicado;
	}

	/**
	 * @return the salario
	 */
	public String getSalario() {
		return salario;
	}

	/**
	 * @param salario the salario to set
	 */
	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return idVaga;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.idVaga = id;
	}

	@Override
	public boolean isDeleted() {
		// TODO Auto-generated method stub
		return false;
	}

}
