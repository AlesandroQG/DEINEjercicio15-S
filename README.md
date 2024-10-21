# Ejercicios 1_5 S - Animales
## DM2 DEIN 2024-2025
### Alesandro Quirós Gobbato

La estructura del proyecto es la siguiente:
- `src > main`:
    - `java > com.alesandro.ejercicio15s`:
        - `AnimalesApplication.java`: Clase que lanza la aplicación
        - `controller`:
            - `AnimalesController.java`: Clase que controla los eventos de la ventana principal de la aplicación
        - `dao`:
            - `DaoAnimal.java`: Clase que hace las operaciones con la base de datos del modelo Animal
        - `db`:
            - `DBConnect.java`: Clase que se conecta a la base de datos
        - `model`:
            - `Animal.java`: Clase que define el objeto Animal
    - `resources`:
        - `fxml`:
            - `ActivarDesactivarAvion.fxml`: Ventana para activar/desactivar aviones
            - `Animales.fxml`: Ventana principal de la aplicación
            - `AniadirAvion.fxml`: Ventana para añadir aviones
            - `BorrarAvion.fxml`: Ventana para borrar aviones
            - `DatosAeropuerto.fxml`: Ventana para añadir un nuevo aeropuerto o editar uno existente
            - `Login.fxml`: Ventana para iniciar sesión en la aplicación
        - `images`: Imágenes de la aplicación
        - `style`: Estilos de la aplicación