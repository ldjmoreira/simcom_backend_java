package com.nelioalves.cursomc.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ComponenteDTO2 implements Serializable {
    // used to the comand insert of resource

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotNull(message = "The age must not be null")
    private Integer quantidade;

    @Column(name = "uuid")
    private Integer uuid;

    private String obs;

    @NotEmpty(message = "The username must not be empty")
    private String fornecedor;

    @NotNull(message = "The validade field must not be null")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "fornecedor_data")
    private Date fornecedorData;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date validade;

    @NotNull(message = "The age must not be null")
    private Integer tipoComponenteId;
    
    
    private Integer userId;

    public ComponenteDTO2(Integer id, @NotNull(message = "The age must not be null") Integer quantidade,
            String obs, @NotEmpty(message = "The username must not be empty") String fornecedor,
            @NotNull(message = "The validade field must not be null") Date fornecedorData, Date validade,
            @NotNull(message = "The age must not be null") Integer tipoComponenteId) {
        super();
        this.id = id;
        this.quantidade = quantidade;
        this.obs = obs;
        this.fornecedor = fornecedor;
        this.fornecedorData = fornecedorData;
        this.validade = validade;
        this.tipoComponenteId = tipoComponenteId;
    }

    public ComponenteDTO2() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
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

    public Integer getTipoComponenteId() {
        return tipoComponenteId;
    }

    public void setTipoComponenteId(Integer tipoComponenteId) {
        this.tipoComponenteId = tipoComponenteId;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
