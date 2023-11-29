package com.example.apiregistrousuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Inicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime modified;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private String token;

    //@JsonIgnore
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "usuarioId"/* , updatable = false */)
    @JsonIgnoreProperties(value="Inicios")
    private Usuario usuario;

    public Inicio() {
    }

    public Inicio(Long id, LocalDateTime modified, LocalDateTime lastLogin, String token, Usuario usuario) {
        this.id = id;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.token = token;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }
}

