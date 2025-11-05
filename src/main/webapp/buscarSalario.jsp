<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/11/2025
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Buscar salario de empleado</title>
</head>
<body>
<h2>Buscar salario por DNI</h2>

<!-- Este formulario enviará los datos al Servlet -->
<<form action="buscarSalario" method="post">
    <label>DNI del empleado:</label>
    <input type="text" name="dni" required>
    <button type="submit">Consultar salario</button>
</form>
</body>
</html>
