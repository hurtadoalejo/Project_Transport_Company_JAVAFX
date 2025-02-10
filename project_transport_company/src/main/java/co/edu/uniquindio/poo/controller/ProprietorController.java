package co.edu.uniquindio.poo.controller;

import java.util.Collection;

import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.TransportCompany;

public class ProprietorController {
    TransportCompany transportCompany;

    /**
     * The constructor method for the class ProprietorController
     * @param transportCompany Transport company of the ProprietorController to create
     */
    public ProprietorController(TransportCompany transportCompany) {
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
     * Method to create a proprietor
     * @param proprietor Proprietor that is supposed to create
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean createProprietor(Proprietor proprietor){
        return transportCompany.addProprietor(proprietor);
    }

    /**
     * Method to delete a proprietor
     * @param id Id of the proprietor to delete
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean deleteProprietor(String id){
        return transportCompany.deleteProprietor(id);
    }

    /**
     * Method to update an proprietor's information
     * @param id Id of the proprietor that is supposed to update
     * @param newProprietor Proprietor with the new information
     * @return Boolean about if the action was done succesfully or not
     */
    public boolean updateProprietor(String id, Proprietor newProprietor){
        return transportCompany.updateProprietor(id, newProprietor);
    }
}