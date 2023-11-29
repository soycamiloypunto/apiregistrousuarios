package com.example.apiregistrousuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="Phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String number;

    @Column
    private String citycode;

    @Column
    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "userId"/* , updatable = false */)
    @JsonIgnoreProperties(value = "phones")
    private Usser user;


    public Phone() {
    }

    public Phone(Long id, String number, String citycode, String contrycode, Usser user) {
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.contrycode = contrycode;
        this.user = user;
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

    public String getContrycode() {
        return contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }

    public Usser getUser() {
        return user;
    }

    public void setUser(Usser user) {
        this.user = user;
    }
}
