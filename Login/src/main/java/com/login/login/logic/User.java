package com.login.login.logic;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
    private String userName;
    private String password;
    @ManyToOne
    @JoinColumn(name="fk_rol")
    private Rol oneRol;
    
    public User() {
    }

    public User(int id, String userName, String password, Rol oneRol) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.oneRol = oneRol;
    }

    public Rol getOneRol() {
        return oneRol;
    }

    public void setOneRol(Rol oneRol) {
        this.oneRol = oneRol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
