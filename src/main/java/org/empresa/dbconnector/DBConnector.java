package org.empresa.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que gestiona la conexión a la base de datos MySQL de la empresa.
 * <p>
 * Permite obtener una conexión activa y cerrar la conexión cuando ya no se necesite.
 * Utiliza el patrón Singleton de conexión reutilizable.
 * </p>
 */
public class DBConnector {

    /**
     * URL de conexión a la base de datos.
     * Formato: "jdbc:mysql://host:puerto/nombre_base_de_datos".
     */
    String url = "jdbc:mysql://localhost:3306/empresa";

    /**
     * Usuario de la base de datos.
     */
    String usuario = "root";

    /**
     * Contraseña del usuario de la base de datos.
     */
    String contrasenya = "123456";

    /**
     * Objeto Connection que representa la conexión activa a la base de datos.
     */
    private Connection connection;

    /**
     * Devuelve una conexión activa a la base de datos.
     * <p>
     * Si ya existía una conexión abierta, la reutiliza.
     * En caso contrario, crea una nueva conexión.
     * </p>
     *
     * @return Objeto {@link Connection} activo.
     */
    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Carga del driver JDBC de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Creación de la conexión
                connection = DriverManager.getConnection(url, usuario, contrasenya);
                System.out.println("La conexión se ha establecido correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * Cierra la conexión actual a la base de datos si está abierta.
     * <p>
     * Se recomienda llamar a este método al finalizar operaciones con la base de datos
     * para liberar recursos.
     * </p>
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Ha habido un error cerrando la conexión: " + e.getMessage());
        }
    }
}
