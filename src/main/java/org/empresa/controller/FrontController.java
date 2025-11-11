package org.empresa.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.empresa.DaoFactory.DAOFactory;
import org.empresa.dao.EmpleadoDAO;
import org.empresa.dao.NominaDAO;
import org.empresa.modelo.Empleado;
import org.empresa.utils.HelperUtils;

import java.io.IOException;
import java.util.List;

/**
 * Servlet FrontController que centraliza todas las peticiones relacionadas con empleados y nóminas.
 * <p>
 * Implementa el patrón Front Controller y View Helper:
 * - Recibe todas las solicitudes desde el cliente.
 * - Determina la acción a realizar (listar, buscar salario, editar empleado, etc.).
 * - Reenvía la información a la plantilla principal con el JSP correspondiente.
 * </p>
 */
@WebServlet("/front")
public class FrontController extends HttpServlet {

    /**
     * Procesa las peticiones GET del cliente.
     * <p>
     * Según el parámetro "opcionGet":
     * - listar: obtiene todos los empleados y muestra la lista.
     * - buscarSalario: redirige al formulario de búsqueda de salario.
     * - editarEmpleado: redirige al formulario de búsqueda/edición de empleado.
     * </p>
     *
     * @param request  Objeto HttpServletRequest con la información de la petición
     * @param response Objeto HttpServletResponse para enviar la respuesta al cliente
     * @throws ServletException en caso de error en el servlet
     * @throws IOException      en caso de error de entrada/salida
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionGet");

        if ("listar".equals(opcion)) {
            EmpleadoDAO empleadoDao = DAOFactory.getEmpleadoDAO();
            List<Empleado> listaEmpleados = empleadoDao.listarEmpleados();
            request.setAttribute("empleados", listaEmpleados);
            request.setAttribute("titulo", "Listado de Empleados");
            request.setAttribute("contenido", "/mostrarDatosEmpleados.jsp");

        } else if ("buscarSalario".equals(opcion)) {
            request.setAttribute("titulo", "Buscar Salario");
            request.setAttribute("contenido", "/buscarSalario.jsp");

        } else if ("editarEmpleado".equals(opcion)) {
            request.setAttribute("titulo", "Buscar Empleado");
            request.setAttribute("contenido", "/buscarEmpleado.jsp");
        }

        // Siempre enviamos a la plantilla principal
        request.getRequestDispatcher("/plantilla.jsp").forward(request, response);
    }

    /**
     * Procesa las peticiones POST del cliente.
     * <p>
     * Según el parámetro "opcionPost":
     * - buscarSalario: busca el salario de un empleado por DNI y lo formatea.
     * - buscarEmpleado: busca un empleado por DNI y lo muestra para edición.
     * - editarEmpleado: actualiza los datos del empleado en la base de datos.
     * </p>
     *
     * @param request  Objeto HttpServletRequest con los parámetros del formulario
     * @param response Objeto HttpServletResponse para enviar la respuesta
     * @throws ServletException en caso de error en el servlet
     * @throws IOException      en caso de error de entrada/salida
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opcion = request.getParameter("opcionPost");
        DAOFactory factory = new DAOFactory();
        EmpleadoDAO empleadoDao = factory.getEmpleadoDAO();
        NominaDAO nominaDAO = factory.getNominaDAO();

        String vista = "/plantilla.jsp"; // Plantilla principal
        String contenido = "";            // JSP parcial que se incluirá

        if ("buscarSalario".equals(opcion)) {
            String dni = request.getParameter("dni");
            int id = empleadoDao.buscarPorDNI(dni).getId();
            Double salario = nominaDAO.buscarNominaPorID(id).getSalario();
            String salarioFormateado = HelperUtils.formatearSalario(salario);

            request.setAttribute("salario", salarioFormateado);
            request.setAttribute("dni", dni);
            contenido = "/buscarSalario.jsp";

        } else if ("buscarEmpleado".equals(opcion)) {
            String dni = request.getParameter("dni");
            Empleado empleado = empleadoDao.buscarPorDNI(dni);
            request.setAttribute("dni", dni);

            if (empleado != null && empleado.getId() != 0) {
                request.setAttribute("empleado", empleado);
            } else {
                request.setAttribute("mensaje", "No se encontró ningún empleado con ese DNI");
            }

            contenido = "/buscarEmpleado.jsp";

        } else if ("editarEmpleado".equals(opcion)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");

            Empleado e = new Empleado(id, nombre, dni);
            boolean actualizado = empleadoDao.actualizar(e);

            request.setAttribute("mensaje",
                    actualizado ? "Empleado actualizado correctamente" : "No se pudo actualizar el empleado");

            contenido = "/buscarEmpleado.jsp";
        }

        // Incluir JSP parcial en la plantilla
        request.setAttribute("contenido", contenido);

        // Enviar siempre a la plantilla principal
        request.getRequestDispatcher(vista).forward(request, response);
    }
}


