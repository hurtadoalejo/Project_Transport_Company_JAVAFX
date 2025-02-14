package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.ProprietorController;
import co.edu.uniquindio.poo.model.Proprietor;
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

public class ProprietorViewController {

    ProprietorController proprietorController;
    ObservableList<Proprietor> proprietorsList = FXCollections.observableArrayList();
    Proprietor selectedProprietor;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Proprietor> tbl_1;

    @FXML
    private TableColumn<Proprietor, String> cl_name;

    @FXML
    private Label lb_phoneNumber;

    @FXML
    private Button bt_deleteProprietor;

    @FXML
    private TextField txt_id;

    @FXML
    private TextField txt_email;

    @FXML
    private Button bt_menu;

    @FXML
    private TextField txt_name;

    @FXML
    private TableColumn<Proprietor, String> cl_email;

    @FXML
    private Button bt_updateProprietor;

    @FXML
    private Label lb_email;

    @FXML
    private TableColumn<Proprietor, String> cl_id;

    @FXML
    private Button bt_clean;

    @FXML
    private Label lbl_proprietorMenu;

    @FXML
    private Button bt_addProprietor;

    @FXML
    private TextField txt_phoneNumber;

    @FXML
    private Label lb_id;

    @FXML
    private Pane pane;

    @FXML
    private TableColumn<Proprietor, String> cl_phoneNumber;

    @FXML
    private Label lb_name;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onAddProprietor() {
        addProprietor();
    }

    @FXML
    void onUpdateProprietor() {
        updateProprietor();
    }

    @FXML
    void onDeleteProprietor() {
        deleteProprietor();
    }

    @FXML
    void onClean() {
        cleanSelection();
    }

    /**
     * Method to create a proprietor with the data written on the text fields
     * @return A proprietor object
     */
    private Proprietor buildProprietor(){
        Proprietor proprietor = new Proprietor(txt_name.getText(), txt_email.getText(), txt_phoneNumber.getText(), txt_id.getText());
        return proprietor;
    }

    /**
     * Method to add a proprietor into the proprietors list
     */
    private void addProprietor(){
        if (verifyFilledFields() && verifyValidFields()) {
            Proprietor proprietor = buildProprietor();
            if (proprietorController.createProprietor(proprietor)) {
                proprietorsList.add(proprietor);
                cleanProprietorsFields();
            }
        }
    }

    /**
     * Method to delete a proprietor from the proprietors list by an id given
     */
    private void deleteProprietor(){
        if (proprietorController.deleteProprietor(selectedProprietor.getId())) {
            proprietorsList.remove(selectedProprietor);
            cleanSelection();
        }
    }

    /**
     * Method to update an proprietor's information
     */
    private void updateProprietor(){
        if (verifyValidFields() && verifyFilledFields()) {
            if (selectedProprietor != null && proprietorController.updateProprietor(selectedProprietor.getId(), buildProprietor())) {
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
        if (!txt_name.getText().isEmpty() && !txt_id.getText().isEmpty() && !txt_email.getText().isEmpty() && !txt_phoneNumber.getText().isEmpty()) {
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
        if (isLong(txt_id.getText()) && isLong(txt_phoneNumber.getText())) {
            valid = true;
        }
        return valid;
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
     * Method to show the proprietor's information in the field texts
     * @param proprietor Proprietor with the information we want to show
     */
    private void showProprietorInformation(Proprietor proprietor){
        if (proprietor != null) {
            txt_name.setText(proprietor.getName());
            txt_id.setText(proprietor.getId());
            txt_email.setText(proprietor.getEmail());
            txt_phoneNumber.setText(proprietor.getPhoneNumber());
        }
    }

    /**
     * Method to obtain the transport company's proprietors list and then assign it to the controller's proprietors list
     */
    private void obtainProprietors() {
        proprietorsList.addAll(proprietorController.obtainProprietorsList());
    }

    /**
     * Method to initialize the view of the proprietor's table
     */
    private void initView() {
        initDataBinding();
        obtainProprietors();
        tbl_1.getItems().clear();
        tbl_1.setItems(proprietorsList);
        listenerSelection();
    }

    /**
     * Method to configure the data types of each column from the proprietor table of the controller
     */
    private void initDataBinding() {
        cl_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        cl_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        cl_email.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        cl_phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
    }

    /**
     * Method to configure the element selection from the proprietor table
     */
    private void listenerSelection() {
        tbl_1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProprietor = newSelection;
            showProprietorInformation(selectedProprietor);
        });
    }

    /**
     * Method to clean the element selection from the proprietors table
     */
    private void cleanSelection(){
        tbl_1.getSelectionModel().clearSelection();
        cleanProprietorsFields();
    }

    /**
     * Method to clean the text fields related with the proprietor's information
     */
    private void cleanProprietorsFields(){
        txt_name.clear();
        txt_id.clear();
        txt_email.clear();
        txt_phoneNumber.clear();
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
        proprietorController = new ProprietorController(App.transportCompany);
        initView();
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert cl_name != null : "fx:id=\"cl_name\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert lb_phoneNumber != null : "fx:id=\"lb_phoneNumber\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert bt_deleteProprietor != null : "fx:id=\"bt_deleteProprietor\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert txt_id != null : "fx:id=\"txt_id\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert txt_email != null : "fx:id=\"txt_email\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert bt_menu != null : "fx:id=\"bt_menu\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert cl_email != null : "fx:id=\"cl_email\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert bt_updateProprietor != null : "fx:id=\"bt_updateProprietor\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert lb_email != null : "fx:id=\"lb_email\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert cl_id != null : "fx:id=\"cl_id\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert lbl_proprietorMenu != null : "fx:id=\"lbl_proprietorMenu\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert bt_addProprietor != null : "fx:id=\"bt_addProprietor\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert txt_phoneNumber != null : "fx:id=\"txt_phoneNumber\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert lb_id != null : "fx:id=\"lb_id\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert cl_phoneNumber != null : "fx:id=\"cl_phoneNumber\" was not injected: check your FXML file 'proprietor.fxml'.";
        assert lb_name != null : "fx:id=\"lb_name\" was not injected: check your FXML file 'proprietor.fxml'.";

    }
}