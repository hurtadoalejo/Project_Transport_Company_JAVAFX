package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class MenuViewController {

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_1;

    @FXML
    private Button bt_3;

    @FXML
    private Button bt_4;

    @FXML
    private Button bt_1;

    @FXML
    private Button bt_2;

    @FXML
    private Pane pane;

    /**
     * Method to initialize the users interface
     */
    @FXML
    void onOpenUsers() {
        app.openUser();
    }

    /**
     * Method to initialize the proprietors interface
     */
    @FXML
    void onOpenProprietors() {
        app.openProprietor();
    }

    /**
     * Method to initialize the vehicles interface
     */
    @FXML
    void onOpenVehicles() {
        app.openVehicle();
    }

    /**
     * Method to initialize the search interface
     */
    @FXML
    void onOpenSearch() {
        //app.openSearch();
    }

    /**
     * Method to assign the principal application for this controller
     * @param app Principal application to assign
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Method to initialize this controller after its FXML file has been loaden
     */
    @FXML
    void initialize() {
        assert lbl_1 != null : "fx:id=\"lbl_1\" was not injected: check your FXML file 'menu.fxml'.";
        assert bt_3 != null : "fx:id=\"bt_3\" was not injected: check your FXML file 'menu.fxml'.";
        assert bt_4 != null : "fx:id=\"bt_4\" was not injected: check your FXML file 'menu.fxml'.";
        assert bt_1 != null : "fx:id=\"bt_1\" was not injected: check your FXML file 'menu.fxml'.";
        assert bt_2 != null : "fx:id=\"bt_2\" was not injected: check your FXML file 'menu.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'menu.fxml'.";

    }
}
