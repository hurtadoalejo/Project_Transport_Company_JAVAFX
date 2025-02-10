package co.edu.uniquindio.poo.controller;

import java.util.Collection;
import java.util.LinkedList;

import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.model.Vehicle;

public class AssociatedController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class AssociatedController
     * @param transportCompany Transport company of the AssociatedController to create
     */
    public AssociatedController(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    /**
     * Method to obtain the transport company's vehicles list
     * @return Transport company's vehicles list
     */
    public Collection<Vehicle> obtainVehiclesList(){
        Collection<Vehicle> vehicles = new LinkedList<>();
        vehicles.addAll(transportCompany.getCargoVehiclesList());
        vehicles.addAll(transportCompany.getPassengerVehiclesList());
        return vehicles;
    }

    /**
     * Method to obtain the transport company's proprietors list
     * @return Transport company's proprietors list
     */
    public Collection<Proprietor> obtainProprietorsList(){
        return transportCompany.getPropietorsList();
    }

    /**
     * Method to add a proprietor associated to a vehicle
     * @param vehicle Vehicle to add a proprietor associated
     * @param proprietor Proprietor to add in the vehicle's associated proprietors list
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean addProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        return transportCompany.addProprietorAssociated(vehicle, proprietor);
    }

    /**
     * Method to delete a proprietor associated from a vehicle
     * @param vehicle Vehicle to delete a proprietor associated
     * @param proprietor Proprietor to delete in the vehicle's associated proprietors list
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean deleteProprietorAssociated(Vehicle vehicle, Proprietor proprietor){
        return transportCompany.deleteProprietorAssociated(vehicle, proprietor);
    }
}