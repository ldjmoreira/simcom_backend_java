package com.nelioalves.cursomc.dto;

public class UserDTO {

    private Integer id;
    private String email;
    private String name;
    
    
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserDTO(Integer id, String email, String name) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
    
    
}
