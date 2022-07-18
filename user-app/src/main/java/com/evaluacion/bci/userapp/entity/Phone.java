package com.evaluacion.bci.userapp.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Phone")
public class Phone {
    
    @Id
    private String number;
    private String citycode;
    private String contrycode;


    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return this.citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return this.contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }

}
