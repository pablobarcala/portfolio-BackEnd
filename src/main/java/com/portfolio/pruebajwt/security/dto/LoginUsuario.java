package com.portfolio.pruebajwt.security.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUsuario {
    @NotBlank(message = "username obligatorio")
    private String nombreUsuario;
    @NotBlank(message = "password obligatorio")
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
