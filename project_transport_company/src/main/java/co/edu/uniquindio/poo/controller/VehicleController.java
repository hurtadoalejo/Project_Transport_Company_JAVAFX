package co.edu.uniquindio.poo.controller;

import java.util.Collection;
import java.util.LinkedList;

import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.model.Vehicle;

public class VehicleController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class VehicleController
     * @param transportCompany Transport company of the VehicleController to create
     */
    public VehicleController(TransportCompany transportCompany) {
        this.transportCompany = transportCompany;
    }

    /**
     * Method to obtain the transport company's proprietors list
     * @return Transport company's proprietors list
     */
    public Collection<Proprietor> obtainProprietorsList(){
        return transportCompany.getPropietorsList();
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
     * Method to create a vehicle
     * @param vehicle Vehicle that is supposed to create
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean createVehicle(Vehicle vehicle){
        return transportCompany.addVehicle(vehicle);
    }

    /**
     * Method to delete a vehicle
     * @param plate Plate of the vehicle to delete
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean deleteVehicle(String plate){
        return transportCompany.deleteVehicle(plate);
    }

    /**
     * Method to update an vehicle's information
     * @param plate Plate of the vehicle that is supposed to update
     * @param newVehicle Vehicle with the new information
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean updateVehicle(String plate, Vehicle newVehicle){
        return transportCompany.updateVehicle(plate, newVehicle);
    }
}