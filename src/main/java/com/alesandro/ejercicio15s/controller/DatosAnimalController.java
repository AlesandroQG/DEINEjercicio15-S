package com.alesandro.ejercicio15s.controller;

import com.alesandro.ejercicio15s.dao.DaoAnimal;
import com.alesandro.ejercicio15s.model.Animal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Clase que controla los eventos de la ventana de datos de animal
 */
public class DatosAnimalController implements Initializable {
    private Animal animal;
    private Blob imagen;

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
        this.imagen = null;
        if (this.animal!=null) {
            txtNombre.setText(animal.getNombre());
            txtEspecie.setText(animal.getEspecie());
            txtRaza.setText(animal.getRaza());
            if (animal.getSexo().equals("Femenino")) {
                rbFemenino.setSelected(true);
            }
            txtEdad.setText(animal.getEdad() + "");
            txtPeso.setText(animal.getPeso() + "");
            txtObservaciones.setText(animal.getObservaciones());
            fecha.setValue(animal.getFecha_primera_consulta());
            if (animal.getFoto() != null) {
                System.out.println("Has image");
                this.imagen = animal.getFoto();
                try {
                    InputStream imagen = animal.getFoto().getBinaryStream();
                    foto.setImage(new Image(imagen));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                btnFotoBorrar.setDisable(false);
            }
        }
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Seleccionar". Abre un FileChooser para seleccionar una imagen
     *
     * @param event
     */
    @FXML
    void seleccionImagen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una foto del animal");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.jpg","*.png"));
        File file = fileChooser.showOpenDialog(null);
        try {
            double kbs = (double) file.length() / 1024;
            if (kbs > 64) {
                alerta("La imagen no puede ser mayor a 64KB");
            } else {
                InputStream imagen = new FileInputStream(file);
                Blob blob = DaoAnimal.convertFileToBlob(file);
                this.imagen = blob;
                foto.setImage(new Image(imagen));
                btnFotoBorrar.setDisable(false);
            }
        } catch (IOException|NullPointerException e) {
            //e.printStackTrace();
            System.out.println("Imagen no seleccionada");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Cancelar". Borra la foto del animal
     *
     * @param event
     */
    @FXML
    void borrarFoto(ActionEvent event) {
        imagen = null;
        foto.setImage(new Image(getClass().getResourceAsStream("/images/animal.png")));
        btnFotoBorrar.setDisable(true);
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Cancelar". Cierra la ventana
     *
     * @param event
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage)txtNombre.getScene().getWindow();
        stage.close();
    }

    /**
     * Función que se ejecuta cuando se pulsa el botón "Guardar". Válida y procesa los datos
     *
     * @param event
     */
    @FXML
    void guardar(ActionEvent event) {
        String error = validar();
        if (!error.isEmpty()) {
            alerta(error);
        } else {
            Animal nuevo = new Animal();
            nuevo.setNombre(txtNombre.getText());
            nuevo.setEspecie(txtEspecie.getText());
            nuevo.setRaza(txtRaza.getText());
            if (rbMasculino.isSelected()) {
                nuevo.setSexo("Masculino");
            } else {
                nuevo.setSexo("Femenino");
            }
            nuevo.setEdad(Integer.parseInt(txtEdad.getText()));
            nuevo.setPeso(Integer.parseInt(txtPeso.getText()));
            nuevo.setObservaciones(txtObservaciones.getText());
            nuevo.setFecha_primera_consulta(fecha.getValue());
            nuevo.setFoto(this.imagen);
            if (this.animal == null) {
                int id = DaoAnimal.insertar(nuevo);
                if (id == -1) {
                    alerta("Ha habido un error almacenando los datos. Por favor vuelva a intentarlo");
                } else {
                    confirmacion("Animal añadido correctamente");
                    this.cancelar(null);
                }
            } else {
                if (DaoAnimal.modificar(this.animal, nuevo)) {
                    confirmacion("Animal actualizado correctamente");
                    this.cancelar(null);
                } else {
                    alerta("Ha habido un error almacenando los datos. Por favor vuelva a intentarlo");
                }
            }
        }
    }

    /**
     * Válida los datos del formulario
     *
     * @return string con posibles errores
     */
    public String validar() {
        String error = "";
        if (txtNombre.getText().isEmpty()) {
            error += "El campo nombre no puede estar vacío\n";
        }
        if (txtEspecie.getText().isEmpty()) {
            error += "El campo especie no puede estar vacío\n";
        }
        if (txtRaza.getText().isEmpty()) {
            error += "El campo raza no puede estar vacío\n";
        }
        if (txtEdad.getText().isEmpty()) {
            error += "El campo edad no puede estar vacío\n";
        } else {
            try {
                Integer.parseInt(txtEdad.getText());
            } catch (NumberFormatException e) {
                error += "El campo edad tiene que ser numérico\n";
            }
        }
        if (txtPeso.getText().isEmpty()) {
            error += "El campo peso no puede estar vacío\n";
        } else {
            try {
                Integer.parseInt(txtPeso.getText());
            } catch (NumberFormatException e) {
                error += "El campo peso tiene que ser numérico\n";
            }
        }
        if (txtObservaciones.getText().isEmpty()) {
            error += "El campo observaciones no puede estar vacío\n";
        }
        if (fecha.getValue() == null) {
            error += "El campo fecha de la primera consulta no puede estar vacío\n";
        }
        return error;
    }

    /**
     * Función que muestra un mensaje de alerta al usuario
     *
     * @param texto contenido de la alerta
     */
    public void alerta(String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle("Error");
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
