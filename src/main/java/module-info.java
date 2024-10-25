module com.alesandro.ejercicio15s {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.alesandro.ejercicio15s to javafx.fxml;
    exports com.alesandro.ejercicio15s;
    exports com.alesandro.ejercicio15s.controller;
    exports com.alesandro.ejercicio15s.model;
    exports com.alesandro.ejercicio15s.dao;
    opens com.alesandro.ejercicio15s.controller to javafx.fxml;
}