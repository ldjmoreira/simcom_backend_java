package com.nelioalves.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReservaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    
    @NotEmpty(message = "The username must not be empty")
    private Integer experimentoId;
    
    @NotNull(message = "The age must not be null")
    private Integer quantidade;
    
    @NotNull(message = "The age must not be null")
    private Integer tipoComponenteId;

	public ReservaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReservaDTO(Integer id, @NotNull(message = "The age must not be null") Integer experimentoId,
			@NotNull(message = "The age must not be null") Integer quantidade,
			@NotNull(message = "The age must not be null") Integer tipoComponenteId) {
		super();
		this.id = id;
		this.experimentoId = experimentoId;
		this.quantidade = quantidade;
		this.tipoComponenteId = tipoComponenteId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExperimentoId() {
		return experimentoId;
	}

	public void setExperimentoId(Integer experimentoId) {
		this.experimentoId = experimentoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(Integer tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}
    
    

}
