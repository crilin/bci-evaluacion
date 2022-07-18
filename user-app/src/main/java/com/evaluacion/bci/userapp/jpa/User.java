package com.evaluacion.bci.userapp.jpa;

import java.util.UUID;

import javax.persistence.Entity;


@Entity
public class User {

    private String uuid;
    private String name;
    private String email;
    private String password;
    private Phone phone;
    private boolean isactive;

    protected User() {
        
    }

    public User(String name, String email, String password, Phone phone){

        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.isactive = true;
    }

    public String getId() {
        return this.uuid;
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

    public Phone getPhone() {
        return this.phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public boolean Isactive() {
        return this.isactive;
    }

    public void setIsactive(boolean active) {
        this.isactive = active;
    }
    
}
