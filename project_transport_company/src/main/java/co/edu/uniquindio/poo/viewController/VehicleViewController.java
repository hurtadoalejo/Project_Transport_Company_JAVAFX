package co.edu.uniquindio.poo.viewController;


import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.VehicleController;
import co.edu.uniquindio.poo.model.CargoVehicle;
import co.edu.uniquindio.poo.model.PassengerVehicle;
import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.Vehicle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class VehicleViewController {

    VehicleController vehicleController;
    ObservableList<Vehicle> vehiclesList = FXCollections.observableArrayList();
    Vehicle selectedVehicle;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Vehicle, String> cl_colour;

    @FXML
    private Label lb_proprietor;

    @FXML
    private Label lbl_vehicle;

    @FXML
    private ComboBox<Proprietor> cb_proprietor;

    @FXML
    private Label lb_model;

    @FXML
    private Button bt_clean;

    @FXML
    private TableColumn<Vehicle, Integer> cl_model;

    @FXML
    private Label lb_plate;

    @FXML
    private Pane pane;

    @FXML
    private TextField txt_plate;

    @FXML
    private TableColumn<Vehicle, String> cl_brand;

    @FXML
    private TableView<Vehicle> tbl_1;

    @FXML
    private TextField txt_brand;

    @FXML
    private TextField txt_maxPassengers;

    @FXML
    private Label lb_maxPassengers;

    @FXML
    private Label lb_brand;

    @FXML
    private TextField txt_model;

    @FXML
    private TextField txt_colour;

    @FXML
    private TextField txt_cargoCapacity;

    @FXML
    private Button bt_deleteVehicle;

    @FXML
    private Button bt_menu;

    @FXML
    private TableColumn<Vehicle, String> cl_plate;

    @FXML
    private Label lb_type;

    @FXML
    private ComboBox<String> cb_type;

    @FXML
    private Label lb_colour;

    @FXML
    private Label lb_axlesNumber;

    @FXML
    private Button bt_updateVehicle;

    @FXML
    private Label lb_cargoCapacity;

    @FXML
    private Button bt_addVehicle;

    @FXML
    private TextField txt_axlesNumber;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onAddVehicle() {
        //addVehicle();
    }

    @FXML
    void onUpdateVehicle() {
        //updateVehicle();
    }

    @FXML
    void onDeleteVehicle() {
        //deleteVehicle();
    }

    @FXML
    void onClean() {
        cleanSelection();
    }

    /**
     * Method to verify if all the fields have text inside them
     * @return Boolean about filled fields
     */
    private boolean verifyFilledFields(){
        boolean filled = false;
        if (!cb_proprietor.getSelectionModel().isEmpty() && !txt_brand.getText().isEmpty() && !txt_colour.getText().isEmpty() && !txt_model.getText().isEmpty() && !txt_plate.getText().isEmpty() 
        && !cb_type.getSelectionModel().isEmpty()) {
            if (cb_type.getSelectionModel().getSelectedItem().equals("Passenger Vehicle") && !txt_maxPassengers.getText().isEmpty()) {
                filled = true;
            }
            else if (cb_type.getSelectionModel().getSelectedItem().equals("Cargo Vehicle") && !txt_axlesNumber.getText().isEmpty() && !txt_cargoCapacity.getText().isEmpty()) {
                filled = true;
            }
        }
        return filled;
    }

    /**
     * Method to verify if all the field differents to String have the valid data
     * @return Boolean about the valid fields
     */
    private boolean verifyValidFields(){
        boolean valid = false;
        if (cb_type.getSelectionModel().getSelectedItem().equals("Passenger Vehicle")) {
            if (isInteger(txt_model.getText()) && isInteger(txt_maxPassengers.getText())) {
                valid = true;
            }
        }
        else if (cb_type.getSelectionModel().getSelectedItem().equals("Cargo Vehicle")) {
            if (isInteger(txt_model.getText()) && isInteger(txt_axlesNumber.getText()) && isDouble(txt_cargoCapacity.getText())) {
                valid = true;
            }
        }
        return valid;
    }

    /**
     * Method to know if a string date is a double date
     * @param text String to verify
     * @return Boolean that confirms if the string is a double date or not
     */
    private boolean isDouble(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to know if a string date is an integer date
     * @param text String to verify
     * @return Boolean that confirms if the string is an integer date or not
     */
    private boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to show the vehicle's information in the field texts
     * @param vehicle Vehicle with the information we want to show
     */
    private void showVehicleInformation(Vehicle vehicle){
        if (vehicle != null) {
            cb_type.setDisable(true);
            txt_plate.setText(vehicle.getPlate());
            txt_brand.setText(vehicle.getBrand());
            txt_colour.setText(vehicle.getColour());
            txt_model.setText(String.valueOf(vehicle.getModel()));
            cb_proprietor.getSelectionModel().select(vehicle.getProprietor());
            showPersonalizedVehicleInformation(vehicle);
        }
    }

    /**
     * Method to show the vehicle information depends on which instance is of
     * @param vehicle Vehicle to show the personalized attributes
     */
    private void showPersonalizedVehicleInformation(Vehicle vehicle){
        if (vehicle instanceof PassengerVehicle) {
            cb_type.getSelectionModel().select("Passenger Vehicle");
            PassengerVehicle passengerVehicle = (PassengerVehicle) vehicle;
            txt_maxPassengers.setText(String.valueOf(passengerVehicle.getMaxPassengers()));
        }
        else if (vehicle instanceof CargoVehicle) {
            cb_type.getSelectionModel().select("Cargo Vehicle");
            CargoVehicle cargoVehicle = (CargoVehicle) vehicle;
            txt_axlesNumber.setText(String.valueOf(cargoVehicle.getAxlesNumber()));
            txt_cargoCapacity.setText(String.valueOf(cargoVehicle.getCargoCapacity()));
        }
    }

    /**
     * Method to obtain the transport company's proprietors list and then assign it to the controller's proprietors list
     */
    private void obtainVehicles() {
        vehiclesList.addAll(vehicleController.obtainVehiclesList());
    }

    /**
     * Method to initialize the view of the vehicles's table
     */
    private void initView() {
        initDataBinding();
        obtainVehicles();
        tbl_1.getItems().clear();
        tbl_1.setItems(vehiclesList);
        listenerSelection();
    }

    /**
     * Method to configure the data types of each column from the vehicle table of the controller
     */
    private void initDataBinding() {
        cl_brand.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));
        cl_plate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlate()));
        cl_colour.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getColour()));
        cl_model.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getModel()).asObject());
    }

    /**
     * Method to configure the element selection from the vehicle table
     */
    private void listenerSelection() {
        tbl_1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedVehicle = newSelection;
            showVehicleInformation(selectedVehicle);
        });
    }

    /**
     * Method to clean the element selection from the vehicles table
     */
    private void cleanSelection(){
        tbl_1.getSelectionModel().clearSelection();
        cleanVehiclesFields();
        lb_axlesNumber.setVisible(false);
        lb_cargoCapacity.setVisible(false);
        lb_maxPassengers.setVisible(false);
        txt_axlesNumber.setVisible(false);
        txt_cargoCapacity.setVisible(false);
        txt_maxPassengers.setVisible(false);
        cb_type.setDisable(false);
    }

    /**
     * Method to clean the text fields related with the vehicle's information
     */
    private void cleanVehiclesFields(){
        txt_plate.clear();
        txt_brand.clear();
        txt_colour.clear();
        txt_model.clear();
        cb_proprietor.getSelectionModel().clearSelection();
        txt_maxPassengers.clear();
        txt_axlesNumber.clear();
        txt_cargoCapacity.clear();
        cb_type.getSelectionModel().clearSelection();
    }

    /**
     * Method to assign the principal application for this controller
     * @param app Principal application to assign
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Method to configure the combobox's visualization, showing just the id of each proprietor
     */
    public void configureComboBox(){
        cb_proprietor.setCellFactory(param -> new ListCell<Proprietor>() {
            @Override
            protected void updateItem(Proprietor proprietor, boolean empty) {
                super.updateItem(proprietor, empty);
                if (empty || proprietor == null) {
                    setText(null);
                } else {
                    setText(proprietor.getId());
                }
            }
        });
    
        cb_proprietor.setButtonCell(new ListCell<Proprietor>() {
            @Override
            protected void updateItem(Proprietor proprietor, boolean empty) {
                super.updateItem(proprietor, empty);
                if (empty || proprietor == null) {
                    setText(null);
                } else {
                    setText(proprietor.getId());
                }
            }
        });
    }

    private void manageSelectionType(){
        String vehicleType = cb_type.getSelectionModel().getSelectedItem();
        if (vehicleType == null) {
            cleanSelection();
            return;
        }
        if (vehicleType.equals("Cargo Vehicle")) {
            lb_maxPassengers.setVisible(false);
            txt_maxPassengers.setVisible(false);
            lb_axlesNumber.setVisible(true);
            txt_axlesNumber.setVisible(true);
            lb_cargoCapacity.setVisible(true);
            txt_cargoCapacity.setVisible(true);
        }
        else if (vehicleType.equals("Passenger Vehicle")) {
            lb_maxPassengers.setVisible(true);
            txt_maxPassengers.setVisible(true);
            lb_axlesNumber.setVisible(false);
            txt_axlesNumber.setVisible(false);
            lb_cargoCapacity.setVisible(false);
            txt_cargoCapacity.setVisible(false);
        }
    }

    /**
     * Method to initialize this controller after its FXML file has been loaden
     */
    @FXML
    void initialize() {
        vehicleController = new VehicleController(App.transportCompany); 
        cb_proprietor.getItems().addAll(vehicleController.obtainProprietorsList());
        cb_type.getItems().addAll("Cargo Vehicle", "Passenger Vehicle");
        cb_type.setOnAction(event -> manageSelectionType());
        manageSelectionType();
        configureComboBox();
        initView();
        assert cl_colour != null : "fx:id=\"cl_colour\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_proprietor != null : "fx:id=\"lb_proprietor\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lbl_vehicle != null : "fx:id=\"lbl_vehicle\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert cb_proprietor != null : "fx:id=\"cb_proprietor\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_model != null : "fx:id=\"lb_model\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert cl_model != null : "fx:id=\"cl_model\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_plate != null : "fx:id=\"lb_plate\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_plate != null : "fx:id=\"txt_plate\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert cl_brand != null : "fx:id=\"cl_brand\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_brand != null : "fx:id=\"txt_brand\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_maxPassengers != null : "fx:id=\"txt_maxPassengers\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_maxPassengers != null : "fx:id=\"lb_maxPassengers\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_brand != null : "fx:id=\"lb_brand\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_model != null : "fx:id=\"txt_model\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_colour != null : "fx:id=\"txt_colour\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_cargoCapacity != null : "fx:id=\"txt_cargoCapacity\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert bt_deleteVehicle != null : "fx:id=\"bt_deleteVehicle\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert bt_menu != null : "fx:id=\"bt_menu\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert cl_plate != null : "fx:id=\"cl_plate\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_type != null : "fx:id=\"lb_type\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert cb_type != null : "fx:id=\"cb_type\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_colour != null : "fx:id=\"lb_colour\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_axlesNumber != null : "fx:id=\"lb_axlesNumber\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert bt_updateVehicle != null : "fx:id=\"bt_updateVehicle\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert lb_cargoCapacity != null : "fx:id=\"lb_cargoCapacity\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert bt_addVehicle != null : "fx:id=\"bt_addVehicle\" was not injected: check your FXML file 'vehicle.fxml'.";
        assert txt_axlesNumber != null : "fx:id=\"txt_axlesNumber\" was not injected: check your FXML file 'vehicle.fxml'.";
    }
}