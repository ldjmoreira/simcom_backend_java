package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "componentes")
public class Componente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "uuid")
	private Integer uuid;

	@ManyToOne
	@JoinColumn(name = "tipo_componente_id")
	private TipoComponente tipoComponente;

	private Integer quantidade;
	private String obs;
	private String fornecedor;

	@Column(name = "fornecedor_data")
	private Date fornecedorData;

	private Date validade;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	


	// Constructors
	public Componente() {
	}

	public Componente(Integer id, Integer quantidade, Integer uuid, String obs, String fornecedor, Date fornecedorData,
			Date validade,
			TipoComponente tipoComponente,
			User user) {
		this.id = id;
		this.uuid = uuid;
		this.tipoComponente = tipoComponente;
		this.quantidade = quantidade;
		this.obs = obs;
		this.fornecedor = fornecedor;
		this.fornecedorData = fornecedorData;
		this.validade = validade;
		this.user = user;

	}
	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getFornecedorData() {
		return fornecedorData;
	}

	public void setFornecedorData(Date fornecedorData) {
		this.fornecedorData = fornecedorData;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public User getUser() {
		return user;
	}

	// Setter
	public void setUser(User user) {
		this.user = user;
	}

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	// Setter
	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

}
