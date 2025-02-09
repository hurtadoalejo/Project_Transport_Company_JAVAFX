package co.edu.uniquindio.poo.controller;

import java.util.Collection;

import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.model.User;

public class UserController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class UserController
     * @param transportCompany Transport company of the UserController to create
     */
    public UserController(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
    
    /**
     * Method to obtain the transport company's users list
     * @return Transport company's users list
     */
    public Collection<User> obtainUsersList(){
        return transportCompany.getUsersList();
    }

    /**
     * Method to create an user
     * @param user User that is supposed to create
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean createUser(User user){
        return transportCompany.addUser(user);
    }

    /**
     * Method to delete an user
     * @param name Name of the user to delete
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean deleteUser(String name){
        return transportCompany.deleteUser(name);
    }

    /**
     * Method to update an user's information
     * @param name Name of the user that is supposed to update
     * @param newUser User with the new information
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean updateUser(String name, User newUser){
        return transportCompany.updateUser(name, newUser);
    }
}