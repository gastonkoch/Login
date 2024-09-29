package com.login.login.logic;

import com.login.login.persistence.PersistenceController;
import java.util.List;

public class Controller {
    PersistenceController persisController; 

    public Controller() {
        persisController = new PersistenceController();
    }
    
    

    public String validateUser(String userName, String password) {
        
        String message = "";
        List<User> listUser = persisController.getUsers();
        for(User user : listUser){
            if(user.getUserName().equals(userName)){
                if(user.getPassword().equals(password)){
                    message = "Usuario y contraseña correctos. Bienvenido!";
                    return message;
                }else{
                    message = "Contraseña incorrecta";   
                    return message;
                }
            }else{
                message = "Usuario inexistente";
            }
        }
        return message;
    }
}
