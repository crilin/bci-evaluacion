package com.evaluacion.bci.userapp.model;

public class Phone {
    
    private String number;
    private String citycode;
    private String countrycode;

    protected Phone(){

    }

    public Phone(String number, String citycode, String countrycode){
        super();
        this.number = number;
        this.citycode = citycode;
        this.countrycode = countrycode;
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

}
