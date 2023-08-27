package com.nelioalves.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.domain.enums.Perfil;
import com.nelioalves.cursomc.domain.enums.TipoCliente;


@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	
	@Column(unique=true)
	private String email;

	private Integer tipo;
	
	@JsonIgnore
	private String senha;
		
	@JsonIgnore
    @OneToMany(mappedBy = "userId")
    private List<DisciplinaComponente> dc = new ArrayList<>();
	
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Componente> c = new ArrayList<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
		
	public User() {
		addPerfil(Perfil.CLIENTE);
	}

	public User(Integer id, String nome, String email,  TipoCliente tipo, String senha, Integer isAdmin) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;

		this.tipo = (tipo==null) ? null : tipo.getCod();
		this.senha = senha;
		
		if(isAdmin == 1) {
			addPerfil(Perfil.ADMIN);
		}else {
			addPerfil(Perfil.CLIENTE);
		}
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	/*
	public Integer getIsAdmin() {
		return id;
	}

	public void setIsAdmin(Integer id) {
		this.id = id;
	}
	*/
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}
	
	public Integer getTipoNumber() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
    public List<DisciplinaComponente> getDc() {
        return dc;
    }

    public void setDc(List<DisciplinaComponente> dc) {
        this.dc = dc;
    }
    
    public List<Componente> getC() {
        return c;
    }

    public void setC(List<Componente> c) {
        this.c = c;
    }
	
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
