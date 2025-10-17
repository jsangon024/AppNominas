package org.empresa.dao;

import org.empresa.dbconnector.DBConnector;
import org.empresa.modelo.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

    private DBConnector dbConnector;

    public List<Empleado> listarEmpleados(){
        List<Empleado> empleados= new ArrayList<>();
        String consulta= "SELECT * FROM empleados";

        Connection conn=null;
        PreparedStatement stmt= null;
        ResultSet rs = null;
        try{
            conn= dbConnector.getConnection();
            stmt= conn.prepareStatement('mariaDB');
            rs= stmt.executeQuery();

            while(rs.next()){

            }
        }
        return empleados;
    }
}
