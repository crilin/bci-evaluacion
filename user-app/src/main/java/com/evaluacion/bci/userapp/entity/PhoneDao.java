package com.evaluacion.bci.userapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="Phone")
public class PhoneDao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number;
    private String citycode;
    private String countrycode;
    @ManyToOne
    @JoinColumn(name="idUser")
    private UserDao user;

    protected PhoneDao() {
        
    }

    public PhoneDao(Long id, String number, String citycode, String countrycode, UserDao user){

        super();
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
        this.user = user;
    }  

    public Long getId() {
        return this.id;
    }

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

    public String getCountrycode() {
        return this.countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public UserDao getUser() {
        return this.user;
    }

    public void setUser(UserDao user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            " number='" + getNumber() + "'" +
            ", citycode='" + getCitycode() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            ", user='" + getUser().toString() + "'" +
            "}";
    }

}
