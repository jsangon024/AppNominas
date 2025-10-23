package org.empresa.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    String url = "jdbc:mysql://localhost:3306/empresa";
    String usuario = "root";
    String contrasenya = "123456";
    private Connection connection;


    /*Devuelve una conexión activa a la BBDD
     Si ya estaba abierta y existe, la reutiliza.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, usuario, contrasenya);
                System.out.println("La conexión se ha establecido correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
        }
        return connection;
    }

    //Cierra la conexión actual si está abierta

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }

        } catch (SQLException e) {
            System.err.println("Ha habido un error cerando la conexion: " + e.getMessage());
        }
    }
}



