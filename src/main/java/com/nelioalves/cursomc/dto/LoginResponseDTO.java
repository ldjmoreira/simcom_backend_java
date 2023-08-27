package com.nelioalves.cursomc.dto;

public class LoginResponseDTO {

    private UserDTO user;
    private String token;
    
    public LoginResponseDTO() {
    }

    public LoginResponseDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
