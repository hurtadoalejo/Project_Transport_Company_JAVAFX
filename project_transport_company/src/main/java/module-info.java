module co.edu.uniquindio.poo {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens co.edu.uniquindio.poo to javafx.fxml;
    exports co.edu.uniquindio.poo;
    exports co.edu.uniquindio.poo.viewController;
    opens co.edu.uniquindio.poo.viewController to javafx.fxml;
}