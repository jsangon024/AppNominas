package org.empresa.DaoFactory;

import org.empresa.dao.EmpleadoDAO;
import org.empresa.dao.NominaDAO;
import org.empresa.dbconnector.DBConnector;

/**
 * Clase factoría (Factory) que proporciona instancias de los DAOs del proyecto.
 * <p>
 * Centraliza la creación de objetos {@link EmpleadoDAO} y {@link NominaDAO},
 * asegurando que todos compartan la misma conexión a la base de datos
 * a través de un único {@link DBConnector}.
 * </p>
 */
public class DAOFactory {

    /**
     * Conector a la base de datos compartido por todos los DAOs.
     */
    private static final DBConnector dbConnector = new DBConnector();

    /**
     * Devuelve una instancia de {@link EmpleadoDAO}.
     * <p>
     * La instancia compartirá el {@link DBConnector} de la factoría,
     * garantizando que se reutilice la misma conexión si es necesario.
     * </p>
     *
     * @return Nueva instancia de {@link EmpleadoDAO} lista para usar.
     */
    public static EmpleadoDAO getEmpleadoDAO() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleadoDAO.setDbConnector(dbConnector);
        return empleadoDAO;
    }

    /**
     * Devuelve una instancia de {@link NominaDAO}.
     * <p>
     * La instancia compartirá el {@link DBConnector} de la factoría,
     * asegurando consistencia en la conexión a la base de datos.
     * </p>
     *
     * @return Nueva instancia de {@link NominaDAO} lista para usar.
     */
    public static NominaDAO getNominaDAO() {
        NominaDAO nominaDAO = new NominaDAO();
        nominaDAO.setDbConnector(dbConnector);
        return nominaDAO;
    }
}

