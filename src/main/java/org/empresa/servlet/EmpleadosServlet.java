package org.empresa.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.empresa.dao.EmpleadoDAO;
import org.empresa.modelo.Empleado;
import jakarta.servlet.*;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

/*
 * Servlet que que obtiene la lista de empleados desde el dao y lo envía a un JSP.
 */
@WebServlet("/empleados")
public class EmpleadosServlet extends HttpServlet {
    private EmpleadoDAO empleadoDao;

    @Override
    public void init() throws ServletException {
        super.init();
        //Inicializa el Dao una vez arranca el server
        empleadoDao = new EmpleadoDAO();
    }
    @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Pedimos la lista de empleados al DAO
        List<Empleado> listaEmpleados = empleadoDao.listarEmpleados();
        //Guardamos la lista en la request con la clave "empleados"
        request.setAttribute("empleados",listaEmpleados);
        // 3) Reenviamos (forward) la request a la JSP que muestra la lista

        RequestDispatcher dispatcher=request.getRequestDispatcher("/mostrarDatosEmpleados.jsp");
        dispatcher.forward(request,response);
    }
}
