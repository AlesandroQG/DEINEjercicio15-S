# Ejercicios 1_5 S - Animales
## DM2 DEIN 2024-2025
### Alesandro Quirós Gobbato

La estructura del proyecto es la siguiente:
- `src > main`:
    - `java > com.alesandro.ejercicio15s`:
        - `AnimalesApplication.java`: Clase que lanza la aplicación
        - `controller`:
            - `AnimalesController.java`: Clase que controla los eventos de la ventana principal de la aplicación
            - `DatosAnimalController.java`: Clase que controla los eventos de la venta para añadir un nuevo animal o editar uno existente
        - `dao`:
            - `DaoAnimal.java`: Clase que hace las operaciones con la base de datos del modelo Animal
        - `db`:
            - `DBConnect.java`: Clase que se conecta a la base de datos
        - `model`:
            - `Animal.java`: Clase que define el objeto Animal
    - `resources`:
        - `fxml`:
            - `Animales.fxml`: Ventana principal de la aplicación
            - `DatosAnimal.fxml`: Ventana para añadir un nuevo animal o editar uno existente
        - `images`: Imágenes de la aplicación
        - `style`: Estilos de la aplicación