package com.alesandro.ejercicio15s.dao;

import com.alesandro.ejercicio15s.db.DBConnect;
import com.alesandro.ejercicio15s.model.Animal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.InputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Clase donde se ejecuta las consultas para la tabla Animales
 */
public class DaoAnimal {
    /**
     * Metodo que busca un animal por medio de su id
     *
     * @param id id del animal a buscar
     * @return animal o null
     */
    public static Animal getAnimal(int id) {
        DBConnect connection;
        Animal animal = null;
        try {
            connection = new DBConnect();
            String consulta = "SELECT id,nombre,especie,raza,sexo,edad,peso,observaciones,fecha_primera_consulta,foto FROM animales WHERE id = ?";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id_animal = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String especie = rs.getString("especie");
                String raza = rs.getString("raza");
                String sexo = rs.getString("sexo");
                int edad = rs.getInt("edad");
                int peso = rs.getInt("peso");
                String observaciones = rs.getString("observaciones");
                LocalDate fecha_primera_consulta = rs.getDate("fecha_primera_consulta").toLocalDate();
                InputStream foto = rs.getBinaryStream("foto");
                animal = new Animal(id_animal,nombre,especie,raza,sexo,edad,peso,observaciones,fecha_primera_consulta,foto);
            }
            rs.close();
            connection.closeConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return animal;
    }

    /**
     * Metodo que carga los datos de la tabla Animal y los devuelve para usarlos en un listado de animales
     *
     * @return listado de animales para cargar en un tableview
     */
    public static ObservableList<Animal> cargarListado() {
        DBConnect connection;
        ObservableList<Animal> animales = FXCollections.observableArrayList();
        try{
            connection = new DBConnect();
            String consulta = "SELECT id,nombre,especie,raza,sexo,edad,peso,observaciones,fecha_primera_consulta,foto FROM animales";
            PreparedStatement pstmt = connection.getConnection().prepareStatement(consulta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id_animal = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String especie = rs.getString("especie");
                String raza = rs.getString("raza");
                String sexo = rs.getString("sexo");
                int edad = rs.getInt("edad");
                int peso = rs.getInt("peso");
                String observaciones = rs.getString("observaciones");
                LocalDate fecha_primera_consulta = rs.getDate("fecha_primera_consulta").toLocalDate();
                InputStream foto = rs.getBinaryStream("foto");
                Animal animal = new Animal(id_animal,nombre,especie,raza,sexo,edad,peso,observaciones,fecha_primera_consulta,foto);
                animales.add(animal);
            }
            rs.close();
            connection.closeConnection();
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return animales;
    }

    /**
     * Metodo que modifica los datos de un animal en la BD
     *
     * @param animal		Instancia del animal con datos nuevos
     * @param animalNuevo Nuevos datos del animal a modificar
     * @return			true/false
     */
    public static boolean modificar(Animal animal, Animal animalNuevo) {
        DBConnect connection;
        PreparedStatement pstmt;
        try {
            connection = new DBConnect();
            String consulta = "UPDATE animales SET nombre = ?,especie = ?,raza = ?,sexo = ?,edad = ?,peso = ?,observaciones = ?,fecha_primera_consulta = ?,foto = ? WHERE id = ?";
            pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt = connection.getConnection().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, animalNuevo.getNombre());
            pstmt.setString(2, animalNuevo.getEspecie());
            pstmt.setString(3, animalNuevo.getRaza());
            pstmt.setString(4, animalNuevo.getSexo());
            pstmt.setInt(5, animalNuevo.getEdad());
            pstmt.setInt(6, animalNuevo.getPeso());
            pstmt.setString(7, animalNuevo.getObservaciones());
            pstmt.setDate(8, Date.valueOf(animalNuevo.getFecha_primera_consulta()));
            pstmt.setBinaryStream(9, animalNuevo.getFoto());
            pstmt.setInt(10,animal.getId());
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Actualizada animal");
            pstmt.close();
            connection.closeConnection();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Metodo que CREA un nuevo un animal en la BD
     *
     * @param animal		Instancia del modelo animal con datos nuevos
     * @return			id/-1
     */
    public  static int insertar(Animal animal) {
        DBConnect connection;
        PreparedStatement pstmt;
        try {
            connection = new DBConnect();
            String consulta = "INSERT INTO animales (nombre,especie,raza,sexo,edad,peso,observaciones,fecha_primera_consulta,foto) VALUES (?,?,?,?,?) ";
            pstmt = connection.getConnection().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, animal.getNombre());
            pstmt.setString(2, animal.getEspecie());
            pstmt.setString(3, animal.getRaza());
            pstmt.setString(4, animal.getSexo());
            pstmt.setInt(5, animal.getEdad());
            pstmt.setInt(6, animal.getPeso());
            pstmt.setString(7, animal.getObservaciones());
            pstmt.setDate(8, Date.valueOf(animal.getFecha_primera_consulta()));
            pstmt.setBinaryStream(9, animal.getFoto());
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Nueva entrada en animal");
            if (filasAfectadas > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    pstmt.close();
                    connection.closeConnection();
                    return id;
                }
            }
            pstmt.close();
            connection.closeConnection();
            return -1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    /**
     * Elimina un animal en función del modelo Animal que le hayamos pasado
     *
     * @param animal Animal a eliminar
     * @return a boolean
     */
    public  static boolean eliminar(Animal animal){
        DBConnect connection;
        PreparedStatement pstmt;
        try {
            connection = new DBConnect();
            String consulta = "DELETE FROM animales WHERE id = ?";
            pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setInt(1, animal.getId());
            int filasAfectadas = pstmt.executeUpdate();
            pstmt.close();
            connection.closeConnection();
            System.out.println("Eliminado con éxito");
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /**
     * Elimina un animal en función del modelo Animal que le hayamos pasado
     *
     * @param aeropuerto Aeropuerto a eliminar
     * @return a boolean
     */
    public  static boolean eliminarPorAeropuerto(Aeropuerto aeropuerto){
        DBConnect connection;
        PreparedStatement pstmt;
        try {
            connection = new DBConnect();
            String consulta = "DELETE FROM animales WHERE id_aeropuerto = ?";
            pstmt = connection.getConnection().prepareStatement(consulta);
            pstmt.setInt(1, aeropuerto.getId());
            int filasAfectadas = pstmt.executeUpdate();
            pstmt.close();
            connection.closeConnection();
            System.out.println("Eliminado con éxito");
            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
