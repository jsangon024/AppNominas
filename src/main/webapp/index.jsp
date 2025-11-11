<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inicio - Empresa</title>
</head>
<body>
<h1>Bienvenido al sistema de gestión de empleados</h1>

<nav>
    <ul>
        <li><a href="${pageContext.request.contextPath}/front?opcionGet=listar">📋 Listar Empleados</a></li>
        <li><a href="${pageContext.request.contextPath}/front?opcionGet=buscarSalario">💰 Buscar Salario</a></li>
        <li><a href="${pageContext.request.contextPath}/front?opcionGet=editarEmpleado">✏️ Editar Empleado</a></li>
    </ul>
</nav>

<hr>

<p>Selecciona una opción del menú para continuar.</p>
</body>
</html>
