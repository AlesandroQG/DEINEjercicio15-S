package com.alesandro.ejercicio15s.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AnimalesController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}