package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;



public class DisciplinaComponenteDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
    @NotNull(message = "The disciplinaId must not be null")
    private Integer disciplinaId;
    

    
    public DisciplinaComponenteDTO() {
    }
    
    public DisciplinaComponenteDTO(Integer disciplinaId) {
        this.disciplinaId = disciplinaId;

    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDisciplinaId() {
		return disciplinaId;
	}

	public void setDisciplinaId(Integer disciplinaId) {
		this.disciplinaId = disciplinaId;
	}

   

}
