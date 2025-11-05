<%--
  Created by IntelliJ IDEA.
  User: usuario_
  Date: 24/10/2025
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Resultado de búsqueda</title>
</head>
<body>

<h2>Resultado de la búsqueda</h2>

<%-- Mostramos el DNI y el salario que nos pasó el servlet --%>
<p><strong>DNI:</strong> ${dni}</p>
<p><strong>Salario:</strong> ${salario} €</p>

<br>
<a href="buscarSalarioEmpleado.jsp">Volver a buscar otro empleado</a>

</body>
</html>
