package co.edu.uniquindio.poo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import co.edu.uniquindio.poo.model.TransportCompany;
import co.edu.uniquindio.poo.viewController.MenuViewController;
import co.edu.uniquindio.poo.viewController.PrimaryViewController;
import co.edu.uniquindio.poo.viewController.UserViewController;

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

    public static void main(String[] args) {
        launch();
    }

}