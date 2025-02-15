package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import co.edu.uniquindio.poo.model.PassengerVehicle;
import co.edu.uniquindio.poo.model.Proprietor;
import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.model.User;
import co.edu.uniquindio.poo.viewController.AssociatedViewController;
import co.edu.uniquindio.poo.viewController.MenuViewController;
import co.edu.uniquindio.poo.viewController.PrimaryViewController;
import co.edu.uniquindio.poo.viewController.ProprietorViewController;
import co.edu.uniquindio.poo.viewController.SearchUserViewController;
import co.edu.uniquindio.poo.viewController.UserToVehicleViewController;
import co.edu.uniquindio.poo.viewController.UserViewController;
import co.edu.uniquindio.poo.viewController.VehicleViewController;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;
    @SuppressWarnings("exports")
    public static TransportCompany transportCompany = new TransportCompany("La Carreta");

    /**
     * Method to initialize the main interface
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Transport Company");
        openPrincipalView();
    }

    /**
     * Method to initialize the principal interface
     */
    public void openPrincipalView() {
        initializeData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("primary.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            PrimaryViewController primaryViewController = loader.getController();
            primaryViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }  

    /**
     * Method to initialize the menu interface
     */
    public void openMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("menu.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            MenuViewController menuViewController = loader.getController();
            menuViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }  

    /**
     * Method to initialize the user menu interface
     */
    public void openUser() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("user.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            UserViewController userViewController = loader.getController();
            userViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    } 

    /**
     * Method to initialize the proprietor menu interface
     */
    public void openProprietor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("proprietor.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            ProprietorViewController proprietorViewController = loader.getController();
            proprietorViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize the vehicle menu interface
     */
    public void openVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("vehicle.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            VehicleViewController vehicleViewController = loader.getController();
            vehicleViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize the associated menu interface
     */
    public void openAssociated() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("associator.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            AssociatedViewController associatedViewController = loader.getController();
            associatedViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize the user to vehicle interface
     */
    public void openUserToVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("userToVehicle.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            UserToVehicleViewController userToVehicleViewController = loader.getController();
            userToVehicleViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Method to initialize the users in vehicle interface
     */
    public void openUsersInVehicle() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("searchUsers.fxml"));
            javafx.scene.layout.Pane rootLayout = (javafx.scene.layout.Pane) loader.load();
            SearchUserViewController searchUserViewController = loader.getController();
            searchUserViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void initializeData(){
        Proprietor proprietor = new Proprietor("Alejandro", "alejo@gmail.com", "3161971519", "1");
        Proprietor proprietor2 = new Proprietor("Veronica", "vero@gmail.com", "3161971519", "2");
        Proprietor proprietor3 = new Proprietor("Sofia", "sofia@gmail.com", "3161971519", "3");
        transportCompany.addProprietor(proprietor);
        transportCompany.addProprietor(proprietor2);
        transportCompany.addProprietor(proprietor3);
        PassengerVehicle passengerVehicle = new PassengerVehicle("VAD01", "Mazda", "Red", 2020, proprietor, 5);
        PassengerVehicle passengerVehicle2 = new PassengerVehicle("VAD02", "Toyota", "Black", 2018, proprietor2, 1);
        transportCompany.addVehicle(passengerVehicle);
        transportCompany.addVehicle(passengerVehicle2);
        User user = new User("Valentina", 22, 56);
        User user2 = new User("Jorge", 40, 87);
        transportCompany.addUser(user);
        transportCompany.addUser(user2);
        transportCompany.addUserToVehicle(passengerVehicle, user);
        transportCompany.addProprietorAssociated(passengerVehicle, proprietor);
    }
}