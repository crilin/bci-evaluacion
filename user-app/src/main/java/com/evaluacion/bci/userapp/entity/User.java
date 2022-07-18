package com.evaluacion.bci.userapp.entity;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.mapping.List;


@Entity
@Table(name="User")
public class User {

    @Id
    private String uuid;

    private String name;
    private String email;
    private String password;
    private List phones;
    private Date created;
    private Date modified;
    private Date last_login;
    private boolean isactive;

    protected User() {
        
    }

    public User(String name, String email, String password, List phones){

        super();
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
        this.created = new Date(System.currentTimeMillis());
        this.modified = new Date(System.currentTimeMillis());
        this.last_login = new Date(System.currentTimeMillis());
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

    public List getphones() {
        return this.phones;
    }

    public void setphones(List phones) {
        this.phones = phones;
    }

    public boolean Isactive() {
        return this.isactive;
    }

    public void setIsactive(boolean active) {
        this.isactive = active;
    }
    
    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return this.modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return this.last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }


}
