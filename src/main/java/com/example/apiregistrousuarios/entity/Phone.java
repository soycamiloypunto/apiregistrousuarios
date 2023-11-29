package com.example.apiregistrousuarios.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name="Phone")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;

    private String citycode;

    private String contrycode;

    @ManyToOne
    @JoinColumn(name = "usserId")
    //@JsonIgnoreProperties(value={"phones"})
    private Usser usser;

    public Phone() {
    }

    public Phone(Integer id, String number, String citycode, String contrycode, Usser usser) {
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.contrycode = contrycode;
        this.usser = usser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Usser getUsser() {
        return usser;
    }

    public void setUsser(Usser usser) {
        this.usser = usser;
    }
}
