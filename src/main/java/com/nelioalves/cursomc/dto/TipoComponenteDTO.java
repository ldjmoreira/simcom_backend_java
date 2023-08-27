package com.nelioalves.cursomc.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TipoComponenteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "The username must not be empty")
    private String nome;

    @NotEmpty(message = "The username must not be empty")
    private String valor;

    @NotNull(message = "The age must not be null")
    private Integer unidadeId;

    @NotEmpty(message = "The username must not be empty")
    private String especificacao;

    @NotNull(message = "The age must not be null")
    private Integer tipo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date validade;

    // Constructors, getters, and setters

    public TipoComponenteDTO() {
    }

    public TipoComponenteDTO(Integer id, String nome, String valor, String especificacao, Integer tipo,
            Date validade, Integer unidadeId) {
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

    // Getter and Setter for 'unidadeId'
    public Integer getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(Integer unidadeId) {
        this.unidadeId = unidadeId;
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
}
