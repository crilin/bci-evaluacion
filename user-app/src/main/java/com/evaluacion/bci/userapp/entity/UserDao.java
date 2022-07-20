package com.evaluacion.bci.userapp.entity;

import java.util.ArrayList;
import java.util.Date;
//import java.util.UUID;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="User")
public class UserDao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private String name;
    private String email;
    private String password;
    private String token;
    
    @OneToMany(targetEntity=PhoneDao.class, mappedBy="user", fetch=FetchType.EAGER)
    private List<PhoneDao> phones;

    private Date created;
    private Date modified;
    private Date last_login;
    private boolean isactive;

    protected UserDao() {
        
    }

    public UserDao(String name, String email, String password, String token){

        super();
        //this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.phones = new ArrayList<PhoneDao>();
        this.created = new Date(System.currentTimeMillis());
        this.modified = new Date(System.currentTimeMillis());
        this.last_login = new Date(System.currentTimeMillis());
        this.isactive = true;
    }

    public String getId() {
        return this.id;
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
    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isIsactive() {
        return this.isactive;
    }

    public boolean getIsactive() {
        return this.isactive;
    }


    public List<PhoneDao> getPhones() {
        return this.phones;
    }

    public void setPhones(List<PhoneDao> phones) {
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


    @Override
    public String toString() {
        return "{" +
            " uuid='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            //", phones='" + getPhones() + "'" +
            ", created='" + getCreated() + "'" +
            ", modified='" + getModified() + "'" +
            ", last_login='" + getLast_login() + "'" +
            ", isactive='" + Isactive() + "'" +
            "}";
    }

}
