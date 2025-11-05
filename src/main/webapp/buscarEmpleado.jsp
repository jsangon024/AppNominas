<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 06/11/2025
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Buscar empleado por DNI</h2>

<form action="buscarEmpleado" method="post">
    <label>DNI:</label>
    <input type="text" name="dni" required>
    <button type="submit">Buscar</button>
</form>

</body>
</html>
