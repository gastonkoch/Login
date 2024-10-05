package com.login.login.logic;

import com.login.login.persistence.PersistenceController;
import java.util.List;

public class Controller {
    PersistenceController persisController; 

    public Controller() {
        persisController = new PersistenceController();
    }
    
    

    public User validateUser(String userName, String password) {
        
        User userLogin = null;
        
        List<User> listUser = persisController.getUsers();
        for(User user : listUser){
            if(user.getUserName().equals(userName)){
                if(user.getPassword().equals(password)){
                    userLogin = user;
                    return userLogin;
                }else{ 
                    userLogin = null;
                    return userLogin;
                }
            }else{
                userLogin = null;
            }
        }
        return userLogin;
    }

    public List<User> getUsers() {
        return persisController.getUsers();
    }

    public List<Rol> getRols() {
        return persisController.getRols();
    }

    public void createUser(String userName, String password, String rolParam) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
       
        Rol rolSearch = new Rol();
        rolSearch = this.getRol(rolParam);
        
        if(rolSearch!=null){
            user.setOneRol(rolSearch);
        }
        
        persisController.createUser(user);
        
    }

    private Rol getRol(String rolParam) {
        List<Rol> listRol = persisController.getRols();
        
        for(Rol r:listRol){
            if(r.getRolName().equals(rolParam)){
                return r;
            }
        };
        
        return null;
        
    }

    public void deleteUser(int id_user) {
        persisController.deleteUser(id_user);
    }

    public User getUserById(int id_usuario) {
        return persisController.getUserById(id_usuario);
    }

    public void updateUser(User user, String userP, String password, String rol) {
        user.setUserName(userP);
        user.setPassword(password);
        
        
        Rol rolSearch = new Rol();
        rolSearch = this.getRol(rol);
        
        if(rolSearch!=null){
            user.setOneRol(rolSearch);
        }
        
        persisController.updateUscer(user);
        
    }


}
