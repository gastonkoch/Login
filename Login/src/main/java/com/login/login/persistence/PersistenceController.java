package com.login.login.persistence;

import com.login.login.logic.Rol;
import com.login.login.logic.User;
import com.login.login.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersistenceController {
    UserJpaController userJpaController = new UserJpaController();
    RolJpaController rolJpaController = new RolJpaController();
            
            

    public List<User> getUsers() {
        return userJpaController.findUserEntities();
    }

    public List<Rol> getRols() {
        return rolJpaController.findRolEntities();
    }

    public void createUser(User user) {
        userJpaController.create(user);
    }

    public void deleteUser(int id_user) {
        try {
            userJpaController.destroy(id_user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getUserById(int id_usuario) {
        return userJpaController.findUser(id_usuario);
    }

    public void updateUscer(User user) {
        try {
            userJpaController.edit(user);
        } catch (Exception ex) {
            Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
