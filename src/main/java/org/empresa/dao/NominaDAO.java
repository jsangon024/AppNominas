package org.empresa.dao;

import org.empresa.dbconnector.DBConnector;
import org.empresa.modelo.Nomina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NominaDAO {

    private DBConnector dbConnector;

    public NominaDAO() {
        dbConnector = new DBConnector();
    }
    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


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
            System.err.println(e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ;
            dbConnector.closeConnection();
        }
        return listaNominas;

    }

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
            System.err.println("No se pudo encontrar nomina: " + e.getMessage());
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

        return nomina;

    }

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
                nomina.setHorasTrabajadas(rs.getInt("horas-trabajadas"));

            }
        } catch (SQLException e) {
            System.err.println("No se pudo encontrar nominas: " + e.getMessage());
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
        return listaNominas;

    }

    public boolean insertar(Nomina n) {
        boolean insertado = false;
        String consulta = "INSERT INTO nomina (empleado_id, salario, fecha_pago, horas_trabajadas) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

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
                System.out.println("La inserción se ha llevado correctamente");
            } else {
                System.out.println("Ha ocurrido un error en la insercion");
            }

        } catch (SQLException e) {
            System.err.println("No se pudo insertar nomina: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return insertado;
    }

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
                System.out.println("Se ha actualizado la nomina");
            } else {
                System.out.println("No se ha podido actualizar la la nomina");
            }

        } catch (SQLException e) {
            System.err.println("No se pudo actualizar la nomina: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return actualizado;
    }

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
                System.out.println("Se ha eliminado correctamente");
            } else {
                System.out.println("No se ha eliminado nada");
            }

        } catch (SQLException e) {
            System.err.println("No se ha podido eliminar la nomina: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dbConnector.closeConnection();
        }
        return eliminado;
    }

}
