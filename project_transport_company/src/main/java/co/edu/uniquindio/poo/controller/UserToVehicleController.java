package co.edu.uniquindio.poo.controller;

import java.util.LinkedList;

import co.edu.uniquindio.poo.model.PassengerVehicle;
import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.model.User;

public class UserToVehicleController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class UserToVehicleController
     * @param transportCompany Transport company of the UserToVehicleController to create
     */
    public UserToVehicleController(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    /**
     * Method to obtain the transport company's passenger vehicles list
     * @return Transport company's passenger vehicles list
     */
    public LinkedList<PassengerVehicle> obtainVehiclesList(){
        return transportCompany.getPassengerVehiclesList();
    }

    /**
     * Method to obtain the transport company's users list
     * @return Transport company's users list
     */
    public LinkedList<User> obtainUsersList(){
        return transportCompany.getUsersList();
    }

    /**
     * Method to add an user associated to a vehicle
     * @param passengerVehicle Passenger vehicle to add an user associated 
     * @param User User to add in the vehicle's associated users list
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean addUserAssociated(PassengerVehicle passengerVehicle, User user){
        return transportCompany.addUserToVehicle(passengerVehicle, user);
    }

    /**
     * Method to delete an user associated from a vehicle
     * @param user User to delete from its vehicle associated
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean deleteUserAssociated(User user){
        return transportCompany.deleteUserFromVehicle(user);
    }
}