module com.alesandro.ejercicio15s {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.alesandro.ejercicio15s to javafx.fxml;
    exports com.alesandro.ejercicio15s;
    exports com.alesandro.ejercicio15s.controller;
    opens com.alesandro.ejercicio15s.controller to javafx.fxml;
}