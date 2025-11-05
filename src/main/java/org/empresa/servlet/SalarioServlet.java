package org.empresa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.empresa.dao.EmpleadoDAO;
import org.empresa.dao.NominaDAO;
import org.empresa.modelo.Empleado;
import org.empresa.modelo.Nomina;

import java.io.IOException;

@WebServlet("/buscarSalario")

public class SalarioServlet extends HttpServlet {

    private EmpleadoDAO empleadoDAO;
    private NominaDAO nominaDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dni = request.getParameter("dni");

        EmpleadoDAO empleadoDao = new EmpleadoDAO();
        NominaDAO nominaDAO = new NominaDAO();

        int id = empleadoDao.buscarPorDNI(dni).getId();
        Double salario = nominaDAO.buscarNominaPorID(id).getSalario();

        request.setAttribute("salario", salario);
        request.setAttribute("dni", dni);

        request.getRequestDispatcher("/mostrarSalarioEmpleado.jsp")
                .forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Para mostrar el formulario directamente si accedes por URL
        request.getRequestDispatcher("/buscarSalarioEmpleado.jsp").forward(request, response);
    }
}
