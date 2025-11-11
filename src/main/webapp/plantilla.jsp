<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${titulo != null ? titulo : 'Empresa'}"/></title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"/>
<jsp:include page="/WEB-INF/includes/menu.jsp"/>

<div style="margin:20px;">
    <!-- Aquí se insertará dinámicamente el contenido -->
    <c:choose>
        <c:when test="${not empty contenido}">
            <jsp:include page="${contenido}" />
        </c:when>
        <c:otherwise>
            <p>No se ha especificado contenido</p>
        </c:otherwise>
    </c:choose>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>
</html>


