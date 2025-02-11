package co.edu.uniquindio.poo.controller;

import co.edu.uniquindio.poo.model.TransportCompany;

public class SearchUserController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class SearchUserController
     * @param transportCompany Transport company of the SearchUserController to create
     */
    public SearchUserController(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }
    
    /**
     * Method to get a personalized message about how many users are in a vehicle
     * @param plate Plate of the vehicle
     * @return A personalized message
     */
    public String getUsersInVehicle(String plate){
        return transportCompany.searchUsersInPassengerVehicle(plate);
    }
}