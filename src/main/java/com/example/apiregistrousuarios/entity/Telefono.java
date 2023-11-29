package com.example.apiregistrousuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String number;

    @Column
    private String citycode;

    @Column
    private String countrycode;

    //@JsonIgnore

    @ManyToOne
    @JoinColumn(name = "usuarioId"/* , updatable = false */)
    @JsonIgnoreProperties(value="telefonos")
    private Usuario usuario;

    public Telefono() {
    }

    public Telefono(Long id, String number, String citycode, String countrycode, Usuario usuario) {
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUser(Usuario usuario) {
        this.usuario = usuario;
    }
}
