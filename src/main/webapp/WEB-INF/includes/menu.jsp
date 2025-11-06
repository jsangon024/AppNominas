<%--
  Created by IntelliJ IDEA.
  User: usuario_
  Date: 06/11/2025
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav style="background-color:#e0e0e0; padding:10px;">
    <a href="front?opcionGet=listar">Listar Empleados</a> |
    <a href="front?opcionGet=buscarSalario">Buscar Salario</a> |
    <a href="front?opcionGet=editarEmpleado">Editar Empleados</a> |
    <a href="${pageContext.request.contextPath}/index.jsp">Inicio</a>
</nav>
