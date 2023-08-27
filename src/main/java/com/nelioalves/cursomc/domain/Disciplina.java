package com.nelioalves.cursomc.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "disciplinas")
public class Disciplina implements Serializable{

	 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
	 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

		@NotEmpty(message="Preenchimento obrigatório")
	    private String nome;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "disciplinaId")
	    private List<DisciplinaComponente> dc = new ArrayList<>();

	    // Constructors, getters, and setters

	    public Disciplina() {
	    	super();
	    }

	    public Disciplina(Integer id, String nome) {
			super();
			this.id = id;
			this.nome = nome;
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

	    public List<DisciplinaComponente> getDc() {
	        return dc;
	    }

	    public void setDc(List<DisciplinaComponente> dc) {
	        this.dc = dc;
	    }
	
}
