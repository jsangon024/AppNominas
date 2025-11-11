package org.empresa.dao;

import org.empresa.dbconnector.DBConnector;
import org.empresa.modelo.Nomina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO (Data Access Object) para la entidad {@link Nomina}.
 * <p>
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * sobre la tabla "nomina" en la base de datos.
 * </p>
 */
public class NominaDAO {

    /**
     * Conector a la base de datos.
     */
    private DBConnector dbConnector;

    /**
     * Constructor por defecto.
     * Inicializa un nuevo {@link DBConnector}.
     */
    public NominaDAO() {
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
     * Obtiene todas las nóminas de la base de datos.
     *
     * @return Lista de {@link Nomina} con todas las nóminas.
     */
    public List<Nomina> listarNominas() {
        List<Nomina> listaNominas = new ArrayList<>();
        String consulta = "SELECT * FROM nomina";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nomina nomina = new Nomina();
                nomina.setId(rs.getInt("id"));
                nomina.setEmpleadoId(rs.getInt("empleado_id"));
                nomina.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                nomina.setSalario(rs.getDouble("salario"));
                nomina.setHorasTrabajadas(rs.getInt("horas_trabajadas"));
                listaNominas.add(nomina);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar nóminas: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return listaNominas;
    }

    /**
     * Busca una nómina por su ID.
     *
     * @param id ID de la nómina a buscar.
     * @return Objeto {@link Nomina} encontrado, o vacío si no existe.
     */
    public Nomina buscarNominaPorID(int id) {
        Nomina nomina = new Nomina();
        String consulta = "SELECT * FROM nomina WHERE id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                nomina.setId(rs.getInt("id"));
                nomina.setEmpleadoId(rs.getInt("empleado_id"));
                nomina.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                nomina.setSalario(rs.getDouble("salario"));
                nomina.setHorasTrabajadas(rs.getInt("horas_trabajadas"));
            }

        } catch (SQLException e) {
            System.err.println("No se pudo encontrar la nómina: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return nomina;
    }

    /**
     * Lista todas las nóminas asociadas a un empleado específico.
     *
     * @param empleadoID ID del empleado.
     * @return Lista de {@link Nomina} asociadas al empleado.
     */
    public List<Nomina> listarPorEmpleado(int empleadoID) {
        List<Nomina> listaNominas = new ArrayList<>();
        String consulta = "SELECT * FROM nomina WHERE empleado_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, empleadoID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nomina nomina = new Nomina();
                nomina.setId(rs.getInt("id"));
                nomina.setEmpleadoId(rs.getInt("empleado_id"));
                nomina.setSalario(rs.getDouble("salario"));
                nomina.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                nomina.setHorasTrabajadas(rs.getInt("horas_trabajadas"));
                listaNominas.add(nomina);
            }
        } catch (SQLException e) {
            System.err.println("No se pudieron encontrar nóminas: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return listaNominas;
    }

    /**
     * Inserta una nueva nómina en la base de datos.
     *
     * @param n Objeto {@link Nomina} a insertar.
     * @return {@code true} si se insertó correctamente, {@code false} en caso contrario.
     */
    public boolean insertar(Nomina n) {
        boolean insertado = false;
        String consulta = "INSERT INTO nomina (empleado_id, salario, fecha_pago, horas_trabajadas) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, n.getEmpleadoId());
            stmt.setDouble(2, n.getSalario());
            stmt.setDate(3, Date.valueOf(n.getFechaPago()));
            stmt.setInt(4, n.getHorasTrabajadas());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                insertado = true;
                System.out.println("La nómina se ha insertado correctamente");
            } else {
                System.out.println("Error al insertar la nómina");
            }

        } catch (SQLException e) {
            System.err.println("No se pudo insertar la nómina: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return insertado;
    }

    /**
     * Actualiza una nómina existente en la base de datos.
     *
     * @param n Objeto {@link Nomina} con los datos actualizados.
     * @return {@code true} si se actualizó correctamente, {@code false} en caso contrario.
     */
    public boolean actualizar(Nomina n) {
        boolean actualizado = false;
        String consulta = "UPDATE nomina SET empleado_id=?, salario=?, fecha_pago=?, horas_trabajadas=? WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, n.getEmpleadoId());
            stmt.setDouble(2, n.getSalario());
            stmt.setDate(3, Date.valueOf(n.getFechaPago()));
            stmt.setInt(4, n.getHorasTrabajadas());
            stmt.setInt(5, n.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                actualizado = true;
                System.out.println("Se ha actualizado la nómina correctamente");
            } else {
                System.out.println("No se pudo actualizar la nómina");
            }

        } catch (SQLException e) {
            System.err.println("Error al actualizar la nómina: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return actualizado;
    }

    /**
     * Elimina una nómina de la base de datos.
     *
     * @param n Objeto {@link Nomina} a eliminar.
     * @return {@code true} si se eliminó correctamente, {@code false} en caso contrario.
     */
    public boolean eliminar(Nomina n) {
        boolean eliminado = false;
        String consulta = "DELETE FROM nomina WHERE id=?";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbConnector.getConnection();
            stmt = conn.prepareStatement(consulta);
            stmt.setInt(1, n.getId());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                eliminado = true;
                System.out.println("La nómina se ha eliminado correctamente");
            } else {
                System.out.println("No se eliminó ninguna nómina");
            }

        } catch (SQLException e) {
            System.err.println("No se pudo eliminar la nómina: " + e.getMessage());
        } finally {
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            dbConnector.closeConnection();
        }

        return eliminado;
    }
}

