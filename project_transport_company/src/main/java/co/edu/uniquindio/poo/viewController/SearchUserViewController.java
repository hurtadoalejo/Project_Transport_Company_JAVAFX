package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.SearchUserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SearchUserViewController {

    SearchUserController searchUserController;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_search;

    @FXML
    private TextField txt_personalizedMessage;

    @FXML
    private Button bt_clean;

    @FXML
    private Label lb_plate;

    @FXML
    private Pane pane;

    @FXML
    private Label lbl_title;

    @FXML
    private TextField txt_plate;

    @FXML
    private Button bt_menu;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onSearch() {
        getUsersInVehicle();
    }

    @FXML
    void onClean() {
        cleanFields();
    }

    /**
     * Method to get a personalized message about how many users are in a vehicle
     * @param plate Plate of the vehicle
     * @return A personalized message
     */
    public void getUsersInVehicle(){
        if (verifyFilledFields()) {
            txt_personalizedMessage.setText(searchUserController.getUsersInVehicle(txt_plate.getText()));
        }
    }

    /**
     * Method to verify if all the fields have text inside them
     * @return Boolean about filled fields
     */
    private boolean verifyFilledFields(){
        boolean filled = false;
        if (!txt_plate.getText().isEmpty()) {
            filled = true;
        }
        return filled;
    }

    /**
     * Method to clean the text fields
     */
    private void cleanFields(){
        txt_plate.clear();
        txt_personalizedMessage.clear();
    }

    /**
     * Method to assign the principal application for this controller
     * @param app Principal application to assign
     */
    public void setApp(App app) {
        this.app = app;
    }

    @FXML
    void initialize() {
        searchUserController = new SearchUserController(App.transportCompany);
        txt_personalizedMessage.setEditable(false);
        assert bt_search != null : "fx:id=\"bt_search\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert txt_personalizedMessage != null : "fx:id=\"txt_personalizedMessage\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert lb_plate != null : "fx:id=\"lb_plate\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert lbl_title != null : "fx:id=\"lbl_title\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert txt_plate != null : "fx:id=\"txt_plate\" was not injected: check your FXML file 'searchUsers.fxml'.";
        assert bt_menu != null : "fx:id=\"bt_menu\" was not injected: check your FXML file 'searchUsers.fxml'.";

    }
}