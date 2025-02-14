package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.UserController;
import co.edu.uniquindio.poo.model.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class UserViewController {

    UserController userController;
    ObservableList<User> usersList = FXCollections.observableArrayList();
    User selectedUser;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<User> tbl_1;

    @FXML
    private TableColumn<User, String> cl_name;

    @FXML
    private TableColumn<User, Double> cl_wight;

    @FXML
    private Button bt_5;

    @FXML
    private TableColumn<User, Integer> cl_age;

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
    private Label lb_3;

    @FXML
    private TextField txt_1;

    @FXML
    private Label lb_4;

    @FXML
    private TextField txt_2;

    @FXML
    private Label lb_2;

    @FXML
    private Pane pane;

    @FXML
    private TextField txt_3;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onAddUser() {
        addUser();
    }

    @FXML
    void onUpdateUser() {
        updateUser();
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
     * Method to create an user with the data written on the text fields
     * @return An user object
     */
    private User buildUser(){
        User user = new User(txt_1.getText(), Integer.parseInt(txt_2.getText()), Double.parseDouble(txt_3.getText()));
        return user;
    }

    /**
     * Method to add an user into the users list
     */
    private void addUser(){
        if (verifyFilledFields() && verifyValidFields()) {
            User user = buildUser();
            if (userController.createUser(user)) {
                usersList.add(user);
                cleanUserFields();
            }
        }
    }

    /**
     * Method to delete an user from the users list by a name given
     */
    private void deleteUser(){
        if (userController.deleteUser(selectedUser.getName())) {
            usersList.remove(selectedUser);
            cleanUserFields();
        }
    }

    /**
     * Method to update an user's information
     */
    private void updateUser(){
        if (verifyFilledFields() && verifyValidFields()) {
            if (selectedUser != null && userController.updateUser(selectedUser.getName(), buildUser())) {
                tbl_1.refresh();
                cleanSelection();
            }
        }
    }

    /**
     * Method to verify if all the fields have text inside them
     * @return Boolean about filled fields
     */
    private boolean verifyFilledFields(){
        boolean filled = false;
        if (!txt_1.getText().isEmpty() && !txt_2.getText().isEmpty() && !txt_3.getText().isEmpty()) {
            filled = true;
        }
        return filled;
    }

    /**
     * Method to verify if all the field differents to String have the valid data
     * @return Boolean about the valid fields
     */
    private boolean verifyValidFields(){
        boolean valid = false;
        if (isLong(txt_2.getText()) && isDouble(txt_3.getText())) {
            valid = true;
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
     * Method to know if a string date is a long date
     * @param text String to verify
     * @return Boolean that confirms if the string is a long date or not
     */
    private boolean isLong(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to show the user's information in the field texts
     * @param user User with the information we want to show
     */
    private void showUserInformation(User user){
        if (user != null) {
            txt_1.setText(user.getName());
            txt_2.setText(String.valueOf(user.getAge()));
            txt_3.setText(String.valueOf(user.getWight()));
        }
    }

    /**
     * Method to obtain the transport company's users list and then assign it to the controller's users list
     */
    private void obtainUsers() {
        usersList.addAll(userController.obtainUsersList());
    }

    /**
     * Method to initialize the view of the user's table
     */
    private void initView() {
        initDataBinding();
        obtainUsers();
        tbl_1.getItems().clear();
        tbl_1.setItems(usersList);
        listenerSelection();
    }

    /**
     * Method to configure the data types of each column from the user table of the controller
     */
    private void initDataBinding() {
        cl_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        cl_age.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAge()).asObject());
        cl_wight.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getWight()).asObject());
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
     * Method to clean the element selection from the user table
     */
    private void cleanSelection(){
        tbl_1.getSelectionModel().clearSelection();
        cleanUserFields();
        txt_1.setDisable(false);
    }

    /**
     * Method to clean the text fields related with the user's information
     */
    private void cleanUserFields(){
        txt_1.clear();
        txt_2.clear();
        txt_3.clear();
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
        userController = new UserController(App.transportCompany);
        initView();
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'user.fxml'.";
        assert cl_name != null : "fx:id=\"cl_name\" was not injected: check your FXML file 'user.fxml'.";
        assert cl_wight != null : "fx:id=\"cl_wight\" was not injected: check your FXML file 'user.fxml'.";
        assert bt_5 != null : "fx:id=\"bt_5\" was not injected: check your FXML file 'user.fxml'.";
        assert cl_age != null : "fx:id=\"cl_age\" was not injected: check your FXML file 'user.fxml'.";
        assert lbl_1 != null : "fx:id=\"lbl_1\" was not injected: check your FXML file 'user.fxml'.";
        assert bt_3 != null : "fx:id=\"bt_3\" was not injected: check your FXML file 'user.fxml'.";
        assert bt_4 != null : "fx:id=\"bt_4\" was not injected: check your FXML file 'user.fxml'.";
        assert bt_1 != null : "fx:id=\"bt_1\" was not injected: check your FXML file 'user.fxml'.";
        assert bt_2 != null : "fx:id=\"bt_2\" was not injected: check your FXML file 'user.fxml'.";
        assert lb_3 != null : "fx:id=\"lb_3\" was not injected: check your FXML file 'user.fxml'.";
        assert txt_1 != null : "fx:id=\"txt_1\" was not injected: check your FXML file 'user.fxml'.";
        assert lb_4 != null : "fx:id=\"lb_4\" was not injected: check your FXML file 'user.fxml'.";
        assert txt_2 != null : "fx:id=\"txt_2\" was not injected: check your FXML file 'user.fxml'.";
        assert lb_2 != null : "fx:id=\"lb_2\" was not injected: check your FXML file 'user.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'user.fxml'.";
        assert txt_3 != null : "fx:id=\"txt_3\" was not injected: check your FXML file 'user.fxml'.";

    }
}
