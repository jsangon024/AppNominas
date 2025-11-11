<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Buscar empleado por DNI</h2>

<form action="front" method="post">
    <label>DNI:</label>
    <input type="text" name="dni" required>
    <input type="hidden" name="opcionPost" value="buscarEmpleado">
    <button type="submit">Buscar</button>
</form>

<c:if test="${not empty mensaje}">
    <p style="color:red;">${mensaje}</p>
</c:if>

<c:if test="${not empty empleado}">
    <h3>Editar empleado</h3>
    <form action="front" method="post">
        <input type="hidden" name="opcionPost" value="editarEmpleado">
        <input type="hidden" name="id" value="${empleado.id}">
        <label>Nombre:</label>
        <input type="text" name="nombre" value="${empleado.nombre}" required><br>
        <label>DNI:</label>
        <input type="text" name="dni" value="${empleado.dni}" required><br>
        <button type="submit">Actualizar</button>
    </form>
</c:if>

<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 06/11/2025
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Buscar empleado por DNI</h2>

<form action="front" method="post">
    <label>DNI:</label>
    <input type="text" name="dni" required>
    <input type="hidden" name="opcionPost" value="buscarEmpleado">
    <button type="submit">Buscar</button>
</form>

<c:if test="${not empty dni}">
    <h2>Editar datos del empleado</h2>

    <form action="front" method="post">
        <input type="hidden" name="opcionPost" value="editarEmpleado">
        <input type="hidden" name="id" value="${empleado.id}">

        <label>Nombre:</label>
        <input type="text" name="nombre" value="${empleado.nombre}" required><br>

        <label>DNI:</label>
        <input type="text" name="dni" value="${empleado.dni}" required><br>

        <button type="submit">Actualizar</button>
    </form>
</c:if>

<p><a href="${pageContext.request.contextPath}">Volver al inicio</a></p>

</body>
</html>--%>


