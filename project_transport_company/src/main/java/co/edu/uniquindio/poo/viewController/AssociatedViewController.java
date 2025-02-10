package co.edu.uniquindio.poo.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.App;
import co.edu.uniquindio.poo.controller.AssociatedController;
import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.Vehicle;
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

public class AssociatedViewController {

    AssociatedController associatedController;
    ObservableList<Proprietor> proprietorsList = FXCollections.observableArrayList();
    Proprietor selectedProprietor;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Vehicle> cb_vehicle;

    @FXML
    private TableView<Proprietor> tbl_1;

    @FXML
    private Label lb_vehicle;

    @FXML
    private TableColumn<Proprietor, String> cl_name;

    @FXML
    private Label lbl_title;

    @FXML
    private Button bt_menu;

    @FXML
    private Button bt_addAssociated;

    @FXML
    private Button bt_clean;

    @FXML
    private TableColumn<Proprietor, String> cl_id;

    @FXML
    private Button bt_deleteAssociated;

    @FXML
    private Label lb_associated;

    @FXML
    private ComboBox<Proprietor> cb_associated;

    @FXML
    private Pane pane;

    @FXML
    void onOpenMenu() {
        app.openMenu();
    }

    @FXML
    void onAddAssociated() {
        addAssociated();
    }

    @FXML
    void onDeleteAssociated() {
        deleteAssociated();
    }

    @FXML
    void onClean() {
        cleanSelection();
    }

    /**
     * Method to add an associated to a vehicle list
     */
    private void addAssociated(){
        if (cb_vehicle.getSelectionModel().getSelectedItem() == null && cb_associated.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (associatedController.addProprietorAssociated(cb_vehicle.getSelectionModel().getSelectedItem(), cb_associated.getSelectionModel().getSelectedItem())) {
            cb_associated.getSelectionModel().clearSelection();
            updateTableList(cb_vehicle.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Method to delete and associated to a vehicle list
     */
    private void deleteAssociated(){
        if (cb_vehicle.getSelectionModel().getSelectedItem() == null && cb_associated.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        if (associatedController.deleteProprietorAssociated(cb_vehicle.getSelectionModel().getSelectedItem(), cb_associated.getSelectionModel().getSelectedItem())) {
            tbl_1.getSelectionModel().clearSelection();
            cb_associated.getSelectionModel().clearSelection();
            updateTableList(cb_vehicle.getSelectionModel().getSelectedItem());
        }
    }

    /**
     * Method to obtain the transport company's associators list and then assign it to the controller's associators list
     */
    private void obtainAssociators(Vehicle vehicle) {
        proprietorsList.removeAll();
        proprietorsList.addAll(vehicle.getAssociatedProprietorList());
    }

    /**
     * Method to configure the data types of each column from the associated table of the controller
     */
    private void initDataBinding() {
        cl_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        cl_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
    }

    /**
     * Method to clean the element selection from the proprietors table
     */
    private void cleanSelection(){
        cb_vehicle.setDisable(false);
        tbl_1.getSelectionModel().clearSelection();
        cb_associated.getSelectionModel().clearSelection();
        cb_vehicle.getSelectionModel().clearSelection();
        tbl_1.getItems().clear();
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
     * Method to show the propietor's information in the field texts
     * @param propietor Propietor with the information we want to show
     */
    private void showProprietorInformation(Proprietor proprietor){
        if (proprietor != null) {
            cb_associated.getSelectionModel().select(proprietor);
            cb_vehicle.setDisable(true);
        }
    }

    /**
     * Method to initialize the view of the proprietor's table
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
     * Method to configure the combobox's visualization, showing just the id of each proprietor
     */
    public void configureComboBoxAssociated(){
        cb_associated.setCellFactory(param -> new ListCell<Proprietor>() {
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
    
        cb_associated.setButtonCell(new ListCell<Proprietor>() {
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

    /**
     * Method to configure the combobox's visualization, showing just the plate of each vehicle
     */
    public void configureComboBoxVehicle(){
        cb_vehicle.setCellFactory(param -> new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);
                if (empty || vehicle == null) {
                    setText(null);
                } else {
                    setText(vehicle.getPlate());
                }
            }
        });
    
        cb_vehicle.setButtonCell(new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);
                if (empty || vehicle == null) {
                    setText(null);
                } else {
                    setText(vehicle.getPlate());
                }
            }
        });
    }

    /**
     * Method to manage the selection of the vehicle
     */
    private void manageSelection(){
        Vehicle vehicle = cb_vehicle.getSelectionModel().getSelectedItem();
        if (vehicle == null) {
            cleanSelection();
            return;
        }
        cb_vehicle.setDisable(true);
        updateTableList(vehicle);
    }

    private void updateTableList(Vehicle vehicle){
        initDataBinding();
        tbl_1.getItems().clear();
        obtainAssociators(vehicle);
        tbl_1.setItems(proprietorsList);
    }

    /**
     * Method to initialize this controller after its FXML file has been loaden
     */
    @FXML
    void initialize() {
        cb_vehicle.setDisable(false);
        associatedController = new AssociatedController(App.transportCompany);
        cb_vehicle.getItems().addAll(associatedController.obtainVehiclesList());
        cb_associated.getItems().addAll(associatedController.obtainProprietorsList());
        cb_vehicle.setOnAction(event -> manageSelection());
        configureComboBoxAssociated();
        configureComboBoxVehicle();
        initView();
        assert cb_vehicle != null : "fx:id=\"cb_vehicle\" was not injected: check your FXML file 'associator.fxml'.";
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'associator.fxml'.";
        assert lb_vehicle != null : "fx:id=\"lb_vehicle\" was not injected: check your FXML file 'associator.fxml'.";
        assert cl_name != null : "fx:id=\"cl_name\" was not injected: check your FXML file 'associator.fxml'.";
        assert lbl_title != null : "fx:id=\"lbl_title\" was not injected: check your FXML file 'associator.fxml'.";
        assert bt_menu != null : "fx:id=\"bt_menu\" was not injected: check your FXML file 'associator.fxml'.";
        assert bt_addAssociated != null : "fx:id=\"bt_addAssociated\" was not injected: check your FXML file 'associator.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'associator.fxml'.";
        assert cl_id != null : "fx:id=\"cl_id\" was not injected: check your FXML file 'associator.fxml'.";
        assert bt_deleteAssociated != null : "fx:id=\"bt_deleteAssociated\" was not injected: check your FXML file 'associator.fxml'.";
        assert lb_associated != null : "fx:id=\"lb_associated\" was not injected: check your FXML file 'associator.fxml'.";
        assert cb_associated != null : "fx:id=\"cb_associated\" was not injected: check your FXML file 'associator.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'associator.fxml'.";

    }
}