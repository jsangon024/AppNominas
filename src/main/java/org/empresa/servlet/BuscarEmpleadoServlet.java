package org.empresa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.empresa.dao.EmpleadoDAO;
import org.empresa.modelo.Empleado;

import java.io.IOException;

@WebServlet("/buscarEmpleado")
public class BuscarEmpleadoServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        // Buscamos el empleado
        Empleado empleado = empleadoDAO.buscarPorDNI(dni);

        if (empleado != null && empleado.getId() != 0) {
            // Guardamos el empleado en el request
            request.setAttribute("empleado", empleado);

            // Reenviamos al JSP de edición
            request.getRequestDispatcher("/editarEmpleado.jsp").forward(request, response);
        } else {
            request.setAttribute("mensaje", "No se encontró ningún empleado con ese DNI");
            request.getRequestDispatcher("/buscarEmpleado.jsp").forward(request, response);
        }
    }
}
