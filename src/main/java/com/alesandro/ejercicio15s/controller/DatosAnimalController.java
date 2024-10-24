package com.alesandro.ejercicio15s.controller;

import com.alesandro.ejercicio15s.dao.DaoAnimal;
import com.alesandro.ejercicio15s.model.Animal;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class DatosAnimalController implements Initializable {
    private Animal animal;

    @FXML // fx:id="btnFotoBorrar"
    private Button btnFotoBorrar; // Value injected by FXMLLoader

    @FXML // fx:id="fecha"
    private DatePicker fecha; // Value injected by FXMLLoader

    @FXML // fx:id="foto"
    private ImageView foto; // Value injected by FXMLLoader

    @FXML // fx:id="rbFemenino"
    private RadioButton rbFemenino; // Value injected by FXMLLoader

    @FXML // fx:id="rbMasculino"
    private RadioButton rbMasculino; // Value injected by FXMLLoader

    @FXML // fx:id="tgSexo"
    private ToggleGroup tgSexo; // Value injected by FXMLLoader

    @FXML // fx:id="txtEdad"
    private TextField txtEdad; // Value injected by FXMLLoader

    @FXML // fx:id="txtEspecie"
    private TextField txtEspecie; // Value injected by FXMLLoader

    @FXML // fx:id="txtNombre"
    private TextField txtNombre; // Value injected by FXMLLoader

    @FXML // fx:id="txtObservaciones"
    private TextField txtObservaciones; // Value injected by FXMLLoader

    @FXML // fx:id="txtPeso"
    private TextField txtPeso; // Value injected by FXMLLoader

    @FXML // fx:id="txtRaza"
    private TextField txtRaza; // Value injected by FXMLLoader

    /**
     * Constructor que define el animal a editar
     *
     * @param animal a editar
     */
    public DatosAnimalController(Animal animal) {
        this.animal = animal;
    }

    /**
     * Constructor vacío para añadir un nuevo animal
     */
    public DatosAnimalController() {
        this.animal = null;
    }

    /**
     * Función que se ejecuta cuando se carga la ventana
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Animal> animales = DaoAnimal.cargarListado();
    }

    @FXML
    void borrarFoto(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void seleccionImagen(ActionEvent event) {

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
