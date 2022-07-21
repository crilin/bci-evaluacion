package com.evaluacion.bci.userapp.model;

import java.util.Date;
import java.util.List;

public class UserDTO {
    
    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
    
    protected UserDTO(){

    }

    public UserDTO(String name, String email, String password, List<PhoneDTO> phones, Date created, Date modified, Date last_login, boolean isactive) {
        
        super();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneDTO> getPhones() {
        return this.phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }
    
    @Override
    public String toString() {
        String msg = "{" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", phones= ['";
            for (PhoneDTO p: getPhones()){
                msg = msg.concat(p.toString());
                msg = msg + ",";
            }
            msg = msg.concat("}");
        return  msg;
    }
   
}
