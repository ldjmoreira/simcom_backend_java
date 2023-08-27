package com.nelioalves.cursomc.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "unidades")
public class Unidade implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    
	@JsonIgnore
	@OneToMany(mappedBy="unidadeId")
	private List<TipoComponente> tpComponente = new ArrayList<>();

    // Constructors, getters, and setters

    public Unidade() {
    }

    public Unidade(String nome) {
        this.nome = nome;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
