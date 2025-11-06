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

import java.io.IOException;
import java.util.List;

@WebServlet("/front")
public class FrontController extends HttpServlet {

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String opcion= request.getParameter("opcionGet");

    if (opcion.equals("listar")){
        EmpleadoDAO empleadoDao = DAOFactory.getEmpleadoDAO();
        List<Empleado> listaEmpleados = empleadoDao.listarEmpleados();
        //Guardamos la lista en la request con la clave "empleados"
        request.setAttribute("empleados",listaEmpleados);
        // 3) Reenviamos (forward) la request a la JSP que muestra la lista

        RequestDispatcher dispatcher=request.getRequestDispatcher("/mostrarDatosEmpleados.jsp");
        dispatcher.forward(request,response);
    } else if (opcion.equals("buscarSalario")){
        RequestDispatcher dispatcher=request.getRequestDispatcher("/buscarSalario.jsp");
        dispatcher.forward(request,response);

    } else if(opcion.equals("editarEmpleado")){
        RequestDispatcher dispatcher= request.getRequestDispatcher("/buscarEmpleado.jsp");
        dispatcher.forward(request,response);
    }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcionPost");

        if ("buscarSalario".equals(opcion)) {
            String dni = request.getParameter("dni");

            EmpleadoDAO empleadoDao = DAOFactory.getEmpleadoDAO();
            NominaDAO nominaDAO = DAOFactory.getNominaDao();

            int id = empleadoDao.buscarPorDNI(dni).getId();
            Double salario = nominaDAO.buscarNominaPorID(id).getSalario();

            request.setAttribute("salario", salario);
            request.setAttribute("dni", dni);

            request.getRequestDispatcher("/buscarSalario.jsp").forward(request, response);

        } else if ("buscarEmpleado".equals(opcion)) {
            EmpleadoDAO empleadoDAO = DAOFactory.getEmpleadoDAO();
            String dni = request.getParameter("dni");

            Empleado empleado = empleadoDAO.buscarPorDNI(dni);
            request.setAttribute("dni",dni);

            if (empleado != null && empleado.getId() != 0) {
                request.setAttribute("empleado", empleado);

            } else {
                request.setAttribute("mensaje", "No se encontró ningún empleado con ese DNI");
            }

            request.getRequestDispatcher("/buscarEmpleado.jsp").forward(request, response);

        } else if ("editarEmpleado".equals(opcion)) {
            EmpleadoDAO empleadoDAO = DAOFactory.getEmpleadoDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String dni = request.getParameter("dni");

            Empleado e = new Empleado(id, nombre, dni);
            boolean actualizado = empleadoDAO.actualizar(e);

            request.setAttribute("mensaje",
                    actualizado ? "Empleado actualizado correctamente" : "No se pudo actualizar el empleado");

            request.getRequestDispatcher("/buscarEmpleado.jsp").forward(request, response);
        }
    }}

