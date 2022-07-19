package com.evaluacion.bci.userapp.model;

import java.util.Date;
import java.util.List;

public class User {
    
    private String id;
    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
    private Date created;
    private Date modified;
    private Date last_login;
    private boolean isactive;
    
    protected User(){

    }

    public User(String id, String name, String email, String password, List<Phone> phones, Date created, Date modified, Date last_login, boolean isactive) {
        
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
        this.created = created;
        this.modified = modified;
        this.last_login = last_login;
        this.isactive = isactive;
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

    public List<Phone> getPhones() {
        return this.phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean Isactive() {
        return this.isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", phones='" + getPhones() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", last_login='" + getLast_login() + "'" +
            ", isactive='" + Isactive() + "'" +
            "}";
    }
   
}
