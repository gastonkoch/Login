
package com.login.login.logic;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String rolName;
    private String rolDescription;
    @OneToMany(mappedBy = "oneRol")
    private List<User> listUser;

    public Rol() {
    }

    public Rol(int id, String rolName, String rolDescription, List<User> listUser) {
        this.id = id;
        this.rolName = rolName;
        this.rolDescription = rolDescription;
        this.listUser = listUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }

    public String getRolDescription() {
        return rolDescription;
    }

    public void setRolDescription(String rolDescription) {
        this.rolDescription = rolDescription;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
    
   
}
