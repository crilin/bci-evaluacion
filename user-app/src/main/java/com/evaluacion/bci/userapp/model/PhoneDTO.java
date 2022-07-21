package com.evaluacion.bci.userapp.model;

public class PhoneDTO {

    private Long id;
    private String number;
    private String citycode;
    private String countrycode;

    protected PhoneDTO(){

    }

    public PhoneDTO(Long id, String number, String citycode, String countrycode){
        super();
        this.id = id;
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", number='" + getNumber() + "'" +
            ", citycode='" + getCitycode() + "'" +
            ", countrycode='" + getCountrycode() + "'" +
            "}";
    }

}
