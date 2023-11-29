package com.example.apiregistrousuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;


    @JsonIgnore
    @Column
    private boolean active;

    @JsonIgnore
    @Column
    private LocalDateTime created;

    @OneToMany(cascade ={CascadeType.ALL}, mappedBy = "usuario")
    @JsonIgnoreProperties(value="usuario")
    //@JsonIgnore
    private List<Telefono> phones;


    @OneToMany(cascade ={CascadeType.ALL}, mappedBy = "usuario")
    @JsonIgnoreProperties(value="usuario")
    @JsonIgnore
    private List<Inicio> logins;

    public Usuario() {
    }

    public Usuario(Long id, String name, String email, String password, boolean active, LocalDateTime created, List<Telefono> phones, List<Inicio> logins) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.created = created;
        this.phones = phones;
        this.logins = logins;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public List<Telefono> getPhones() {
        return phones;
    }

    public void setPhones(List<Telefono> phones) {
        this.phones = phones;
    }

    public List<Inicio> getLogins() {
        return logins;
    }

    public void setLogins(List<Inicio> logins) {
        this.logins = logins;
    }
}