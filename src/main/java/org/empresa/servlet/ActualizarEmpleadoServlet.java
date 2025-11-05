package org.empresa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.empresa.dao.EmpleadoDAO;
import org.empresa.modelo.Empleado;

import java.io.IOException;

@WebServlet("/actualizarEmpleado")
public class ActualizarEmpleadoServlet extends HttpServlet {
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");

        Empleado e = new Empleado();
        e.setId(id);
        e.setNombre(nombre);
        e.setDni(dni);

        boolean actualizado = empleadoDAO.actualizar(e);

        if (actualizado) {
            request.setAttribute("mensaje", "Empleado actualizado correctamente");
        } else {
            request.setAttribute("mensaje", "No se pudo actualizar el empleado");
        }

        request.getRequestDispatcher("/buscarEmpleado.jsp").forward(request, response);
    }
}
