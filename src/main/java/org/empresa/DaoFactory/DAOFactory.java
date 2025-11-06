package org.empresa.DaoFactory;

import org.empresa.dao.EmpleadoDAO;
import org.empresa.dao.NominaDAO;

public class DAOFactory {

    public static EmpleadoDAO getEmpleadoDAO(){
        return new EmpleadoDAO();
    }

    public static NominaDAO getNominaDao(){
        return new NominaDAO();
    }
}
