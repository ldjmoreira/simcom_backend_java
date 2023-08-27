package com.nelioalves.cursomc.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tipo_componentes")
public class TipoComponente implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String valor;

	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private Unidade unidadeId;

	private String especificacao;

	private Integer tipo;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date validade;

	@JsonIgnore
	@OneToMany(mappedBy = "tipoComponente")
	private List<Componente> componentes = new ArrayList<>();

	// Constructors, getters, and setters

	public TipoComponente() {
	}

	public TipoComponente(Integer id, String nome, String valor, String especificacao, Integer tipo, Date validade,
			Unidade unidadeId) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.unidadeId = unidadeId;
		this.especificacao = especificacao;
		this.tipo = tipo;
		this.validade = validade;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEspecificacao() {
		return especificacao;
	}

	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public List<Componente> getComponentes() {
		return componentes;
	}

	public void setComponentes(List<Componente> componentes) {
		this.componentes = componentes;
	}

	public Unidade getUnidadeId() {
		return unidadeId;
	}

	// Setter
	public void setUnidadeId(Unidade unidadeId) {
		this.unidadeId = unidadeId;
	}
}
