package br.com.bestjob.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.bestjob.model.enumerado.EEstado;

@Entity
public class Municipio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long idMunicipio;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private EEstado estado;
	
	public Municipio() {
		
	}

	/**
	 * @return the idMunicipio
	 */
	public Long getIdMunicipio() {
		return idMunicipio;
	}

	/**
	 * @param idMunicipio the idMunicipio to set
	 */
	public void setIdMunicipio(Long idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the estado
	 */
	public EEstado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EEstado estado) {
		this.estado = estado;
	}
	
	

}
