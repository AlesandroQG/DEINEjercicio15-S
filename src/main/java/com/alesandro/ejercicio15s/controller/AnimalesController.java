package com.alesandro.ejercicio15s.controller;

import com.alesandro.ejercicio15s.dao.DaoAnimal;
import com.alesandro.ejercicio15s.model.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase que controla los eventos de la ventana
 */
public class AnimalesController implements Initializable {
    @FXML // fx:id="btnEditar"
    private MenuItem btnEditar; // Value injected by FXMLLoader

    @FXML // fx:id="btnEliminar"
    private MenuItem btnEliminar; // Value injected by FXMLLoader

    @FXML // fx:id="filtroNombre"
    private TextField filtroNombre; // Value injected by FXMLLoader

    @FXML // fx:id="tabla"
    private TableView<Animal> tabla; // Value injected by FXMLLoader

    private ObservableList<Animal> masterData = FXCollections.observableArrayList();
    private ObservableList<Animal> filteredData = FXCollections.observableArrayList();

    /**
     * Función que se ejecuta cuando se carga la ventana
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarTabla();
        cargarAnimales();
    }

    /**
     * Función que carga los animales a la lista
     */
    public void cargarAnimales() {
        filtroNombre.setText(null);
        tabla.getItems().clear();
        masterData.clear();
        filteredData.clear();
        ObservableList<Animal> animales = DaoAnimal.cargarListado();
        masterData.addAll(animales);
        tabla.setItems(animales);
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Editar". Abre una ventana para editar el animal seleccionado
     *
     * @param event
     */
    @FXML
    void editar(ActionEvent event) {
        Animal animal = tabla.getSelectionModel().getSelectedItem();
        if (animal == null) {
            alerta("Tienes que seleccionar un animal de la lista");
        } else {
            try {
                Window ventana = tabla.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Animal.fxml"));
                DatosAnimalController controlador = new DatosAnimalController(animal);
                fxmlLoader.setController(controlador);
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                //stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Olimpiadas.png")));
                stage.setTitle("Editar Animal");
                stage.initOwner(ventana);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                cargarAnimales();
            } catch (IOException e) {
                System.err.println(e.getMessage());
                alerta("Error abriendo ventana, por favor inténtelo de nuevo");
            }
        }
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Eliminar". Elimina un animal
     *
     * @param event
     */
    @FXML
    void eliminar(ActionEvent event) {
        Animal animal = tabla.getSelectionModel().getSelectedItem();
        if (animal == null) {
            alerta("Tienes que seleccionar un animal de la lista");
        } else {
            //
        }
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Nuevo". Abre una ventana para añadir objetos de la tabla seleccionada
     *
     * @param event
     */
    @FXML
    void nuevo(ActionEvent event) {
        try {
            Window ventana = tabla.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Animal.fxml"));
            DatosAnimalController controlador = new DatosAnimalController();
            fxmlLoader.setController(controlador);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            //stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/Olimpiadas.png")));
            stage.setTitle("Añadir Animal");
            stage.initOwner(ventana);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            cargarAnimales();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            alerta("Error abriendo ventana, por favor inténtelo de nuevo");
        }
    }

    /**
     * Función que carga las columnas de la tabla
     */
    public void cargarTabla() {
        //
    }

    /**
     * Función que muestra un mensaje de alerta al usuario
     *
     * @param texto contenido de la alerta
     */
    public void alerta(String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("ERROR");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

    /**
     * Función que muestra un mensaje de confirmación al usuario
     *
     * @param texto contenido del mensaje
     */
    public void confirmacion(String texto) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle("Info");
        alerta.setContentText(texto);
        alerta.showAndWait();
    }

}