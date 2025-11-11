package org.empresa.DaoFactory;

import org.empresa.dao.EmpleadoDAO;
import org.empresa.dao.NominaDAO;
import org.empresa.dbconnector.DBConnector;

public class DAOFactory {

    private static final DBConnector dbConnector = new DBConnector();

    public static EmpleadoDAO getEmpleadoDAO() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.setDbConnector(dbConnector);
        return empleadoDAO;
    }

    public static NominaDAO getNominaDAO() {
        NominaDAO nominaDAO = new NominaDAO();
        nominaDAO.setDbConnector(dbConnector);
        return nominaDAO;
    }
}
