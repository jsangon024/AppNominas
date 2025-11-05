<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 06/11/2025
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Editar empleado</title>
</head>
<body>
<h2>Editar datos del empleado</h2>

<form action="actualizarEmpleado" method="post">
    <input type="hidden" name="id" value="${empleado.id}">

    <label>Nombre:</label>
    <input type="text" name="nombre" value="${empleado.nombre}" required><br>

    <label>DNI:</label>
    <input type="text" name="dni" value="${empleado.dni}" required><br>

    <button type="submit">Actualizar</button>
</form>

</body>
</html>

