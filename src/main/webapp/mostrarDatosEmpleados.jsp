<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Lista de empleados</h2>
<table border="1" cellpadding="4">
    <tr><th>ID</th><th>Nombre</th><th>DNI</th></tr>
    <c:forEach var="emp" items="${empleados}">
        <tr>
            <td>${emp.id}</td>
            <td>${emp.nombre}</td>
            <td>${emp.dni}</td>
        </tr>
    </c:forEach>
</table>

<%--
  Created by IntelliJ IDEA.
  User: usuario_
  Date: 24/10/2025
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Empleados</h1>

<c:if test="${empty empleados}">
    <p>No hay empleados para mostrar</p>
</c:if>

<c:if test="${not empty empleados}">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Dni</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${empleados}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.nombre}</td>
                <td>${emp.dni}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<p><a href="${pageContext.request.contextPath}/index.jsp">Volver al inicio</a></p>

</body>
</html>--%>
