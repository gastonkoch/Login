package com.login.login.persistence;

import com.login.login.logic.User;
import java.util.List;


public class PersistenceController {
    UserJpaController userJpaController = new UserJpaController();

    public List<User> getUsers() {
        return userJpaController.findUserEntities();
    }


}
