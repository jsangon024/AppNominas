package org.empresa.dao;

import org.empresa.dbconnector.DBConnector;
import org.empresa.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private DBConnector dbConnector;

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
            //Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return empleados;
    }

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
            System.err.println("Error a encontrar empleados: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return empleado;
    }

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
                System.out.println("El usuario se ha insertado satisfactoriamente");
            }

        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                System.err.println("El dni ya existe");
            } else {
                System.err.println("Error al insertar empleado: " + ex.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return insertado;
    }

    public boolean actualizar(Empleado e) {
        boolean insertado = false;
        String consulta = "UPDATE  empleados SET nombre=?, dni=? where id=?";

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
                insertado = true;
                System.out.println("Se ha actualizado el Empleado correctamente");
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {//DNI duplicado
                System.err.println("Dni ya existente");
            } else {
                System.err.println("Error al actualizar empleado: " + ex.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dbConnector.closeConnection();
        }

        return insertado;
    }

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
            if (ex.getErrorCode() == 1451) { //Error por restricción por clave foránea.
                System.err.println("No se puede eliminar el empleado: tiene nominas asociadas");
            } else {
                System.err.println("Error al eliminar empleado: " + ex.getMessage());
            }
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return eliminado;
    }

}
