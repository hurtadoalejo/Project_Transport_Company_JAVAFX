package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.UserToVehicleController;
import co.edu.uniquindio.poo.model.PassengerVehicle;
import co.edu.uniquindio.poo.model.User;
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
import javafx.scene.layout.Pane;

public class UserToVehicleViewController {

    UserToVehicleController userToVehicleController;
    ObservableList<User> usersList = FXCollections.observableArrayList();
    User selectedUser;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<PassengerVehicle> cb_vehicle;

    @FXML
    private TableView<User> tbl_1;

    @FXML
    private Label lb_vehicle;

    @FXML
    private TableColumn<User, String> cl_name;

    @FXML
    private Button bt_addUser;

    @FXML
    private TableColumn<User, Integer> cl_age;

    @FXML
    private Label lbl_title;

    @FXML
    private Button bt_menu;

    @FXML
    private Button bt_deleteUser;

    @FXML
    private Button bt_clean;

    @FXML
    private Label lb_associated;

    @FXML
    private Pane pane;

    @FXML
    private ComboBox<User> cb_user;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onAddUser() {
        addUser();
    }

    @FXML
    void onDeleteUser() {
        deleteUser();
    }

    @FXML
    void onClean() {
        cleanSelection();
    }

    /**
     * Method to add an user to a vehicle list
     */
    private void addUser(){
        if (cb_vehicle.getSelectionModel().getSelectedItem() == null && cb_user.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (userToVehicleController.addUserAssociated(cb_vehicle.getSelectionModel().getSelectedItem(), cb_user.getSelectionModel().getSelectedItem())) {
            cb_user.getSelectionModel().clearSelection();
            updateTableList(cb_vehicle.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Method to delete an user to a vehicle list
     */
    private void deleteUser(){
        if (cb_vehicle.getSelectionModel().getSelectedItem() == null && cb_user.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (userToVehicleController.deleteUserAssociated(selectedUser)) {
            tbl_1.getSelectionModel().clearSelection();
            cb_user.getSelectionModel().clearSelection();
            updateTableList(cb_vehicle.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Method to obtain the transport company's users list and then assign it to the controller's users list
     */
    private void obtainUsers(PassengerVehicle passengerVehicle) {
        usersList.removeAll();
        usersList.addAll(passengerVehicle.getAssociatedUsersList());
    }

    /**
     * Method to configure the data types of each column from the user table of the controller
     */
    private void initDataBinding() {
        cl_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        cl_age.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
    }

    /**
     * Method to clean the element selection from the user's table
     */
    private void cleanSelection(){
        cb_vehicle.setDisable(false);
        tbl_1.getSelectionModel().clearSelection();
        cb_user.getSelectionModel().clearSelection();
        cb_vehicle.getSelectionModel().clearSelection();
        tbl_1.getItems().clear();
    }

    /**
     * Method to configure the element selection from the user table
     */
    private void listenerSelection() {
        tbl_1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedUser = newSelection;
            showUserInformation(selectedUser);
        });
    }

    /**
     * Method to show the user's information in the field texts
     * @param user User with the information we want to show
     */
    private void showUserInformation(User user){
        if (user != null) {
            cb_user.getSelectionModel().select(user);
            cb_vehicle.setDisable(true);
        }
    }

    /**
     * Method to initialize the view of the user's table
     */
    private void initView() {
        initDataBinding();
        listenerSelection();
    }

    /**
     * Method to assign the principal application for this controller
     * @param app Principal application to assign
     */
    public void setApp(App app) {
        this.app = app;
    }

    /**
     * Method to configure the combobox's visualization, showing just the name of each user
     */
    public void configureComboBoxUser(){
        cb_user.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getName());
                }
            }
        });
    
        cb_user.setButtonCell(new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getName());
                }
            }
        });
    }

    /**
     * Method to configure the combobox's visualization, showing just the plate of each vehicle
     */
    public void configureComboBoxVehicle(){
        cb_vehicle.setCellFactory(param -> new ListCell<PassengerVehicle>() {
            @Override
            protected void updateItem(PassengerVehicle passengerVehicle, boolean empty) {
                super.updateItem(passengerVehicle, empty);
                if (empty || passengerVehicle == null) {
                    setText(null);
                } else {
                    setText(passengerVehicle.getPlate());
                }
            }
        });
    
        cb_vehicle.setButtonCell(new ListCell<PassengerVehicle>() {
            @Override
            protected void updateItem(PassengerVehicle passengerVehicle, boolean empty) {
                super.updateItem(passengerVehicle, empty);
                if (empty || passengerVehicle == null) {
                    setText(null);
                } else {
                    setText(passengerVehicle.getPlate());
                }
            }
        });
    }

    /**
     * Method to manage the selection of the vehicle
     */
    private void manageSelection(){
        PassengerVehicle passengerVehicle = cb_vehicle.getSelectionModel().getSelectedItem();
        if (passengerVehicle == null) {
            cleanSelection();
            return;
        }
        cb_vehicle.setDisable(true);
        updateTableList(passengerVehicle);
    }

    /**
     * Method to update the table list depending on the vehicle
     * @param vehicle
     */
    private void updateTableList(PassengerVehicle passengerVehicle){
        initDataBinding();
        tbl_1.getItems().clear();
        obtainUsers(passengerVehicle);
        tbl_1.setItems(usersList);
    }

    /**
     * Method to initialize this controller after its FXML file has been loaden
     */
    @FXML
    void initialize() {
        cb_vehicle.setDisable(false);
        userToVehicleController = new UserToVehicleController(App.transportCompany);
        cb_vehicle.getItems().addAll(userToVehicleController.obtainVehiclesList());
        cb_user.getItems().addAll(userToVehicleController.obtainUsersList());
        cb_vehicle.setOnAction(event -> manageSelection());
        configureComboBoxUser();
        configureComboBoxVehicle();
        initView();
        assert cb_vehicle != null : "fx:id=\"cb_vehicle\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert lb_vehicle != null : "fx:id=\"lb_vehicle\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert cl_name != null : "fx:id=\"cl_name\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert bt_addUser != null : "fx:id=\"bt_addUser\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert cl_age != null : "fx:id=\"cl_age\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert lbl_title != null : "fx:id=\"lbl_title\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert bt_menu != null : "fx:id=\"bt_menu\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert bt_deleteUser != null : "fx:id=\"bt_deleteUser\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert lb_associated != null : "fx:id=\"lb_associated\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'userToVehicle.fxml'.";
        assert cb_user != null : "fx:id=\"cb_user\" was not injected: check your FXML file 'userToVehicle.fxml'.";

    }
}