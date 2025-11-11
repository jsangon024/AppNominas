package org.empresa.dao;

import org.empresa.dbconnector.DBConnector;
import org.empresa.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para la entidad {@link Empleado}.
 * <p>
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la tabla "empleados" en la base de datos.
 * </p>
 */
public class EmpleadoDAO {

    /**
     * Conector a la base de datos.
     */
    private DBConnector dbConnector;

    /**
     * Constructor por defecto.
     * Inicializa un nuevo {@link DBConnector}.
     */
    public EmpleadoDAO() {
        dbConnector = new DBConnector();
    }

    /**
     * Permite inyectar un {@link DBConnector} externo.
     *
     * @param dbConnector Instancia de {@link DBConnector} a usar.
     */
    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }

    /**
     * Obtiene todos los empleados de la base de datos.
     *
     * @return Lista de {@link Empleado} con todos los empleados.
     */
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String consulta = "SELECT * FROM empleados";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Empleado emp = new Empleado();
                emp.setId(rs.getInt("id"));
                emp.setDni(rs.getString("dni"));
                emp.setNombre(rs.getString("nombre"));
                empleados.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return empleados;
    }

    /**
     * Busca un empleado por su ID.
     *
     * @param id ID del empleado a buscar.
     * @return Objeto {@link Empleado} encontrado, o vacío si no existe.
     */
    public Empleado buscarPorID(int id) {
        Empleado empleado = new Empleado();
        String consulta = "SELECT * FROM empleados WHERE id= ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
            } else {
                System.out.println("No se pudo encontrar el empleado");
            }
        } catch (SQLException e) {
            System.err.println("Error al encontrar empleado: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return empleado;
    }

    /**
     * Busca un empleado por su DNI.
     *
     * @param dni DNI del empleado a buscar.
     * @return Objeto {@link Empleado} encontrado, o vacío si no existe.
     */
    public Empleado buscarPorDNI(String dni) {
        Empleado empleado = new Empleado();
        String consulta = "SELECT * FROM empleados WHERE dni= ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setString(1, dni);
            rs = stmt.executeQuery();

            if (rs.next()) {
                empleado.setId(rs.getInt("id"));
                empleado.setDni(rs.getString("dni"));
                empleado.setNombre(rs.getString("nombre"));
            } else {
                System.out.println("No se pudo encontrar el empleado");
            }
        } catch (SQLException e) {
            System.err.println("Error al encontrar empleado: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return empleado;
    }

    /**
     * Inserta un nuevo empleado en la base de datos.
     *
     * @param e Objeto {@link Empleado} a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} en caso contrario.
     */
    public boolean insertar(Empleado e) {
        String consulta = "INSERT INTO empleados (nombre, dni) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        boolean insertado = false;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getDni());
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                insertado = true;
                System.out.println("El empleado se ha insertado satisfactoriamente");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("El DNI ya existe");
            } else {
                System.err.println("Error al insertar empleado: " + ex.getMessage());
            }
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return insertado;
    }

    /**
     * Actualiza los datos de un empleado existente en la base de datos.
     *
     * @param e Objeto {@link Empleado} con los datos actualizados.
     * @return {@code true} si se actualizó correctamente, {@code false} en caso contrario.
     */
    public boolean actualizar(Empleado e) {
        boolean actualizado = false;
        String consulta = "UPDATE empleados SET nombre=?, dni=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setString(1, e.getNombre());
            stmt.setString(2, e.getDni());
            stmt.setInt(3, e.getId());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                actualizado = true;
                System.out.println("Se ha actualizado el empleado correctamente");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("DNI duplicado");
            } else {
                System.err.println("Error al actualizar empleado: " + ex.getMessage());
            }
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return actualizado;
    }

    /**
     * Elimina un empleado de la base de datos.
     *
     * @param e Objeto {@link Empleado} a eliminar.
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario.
     */
    public boolean eliminar(Empleado e) {
        boolean eliminado = false;
        String consulta = "DELETE FROM empleados WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, e.getId());
            int filas = stmt.executeUpdate();

            if (filas > 0) {
                eliminado = true;
                System.out.println("El empleado se ha eliminado correctamente");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                System.err.println("No se puede eliminar el empleado: tiene nóminas asociadas");
            } else {
                System.err.println("Error al eliminar empleado: " + ex.getMessage());
            }
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException ex) { ex.printStackTrace(); }
            dbConnector.closeConnection();
        }
        return eliminado;
    }
}
