<%--
  Created by IntelliJ IDEA.
  User: usuario_
  Date: 06/11/2025
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${param.titulo}</title>
</head>
<body>

<jsp:include page="/WEB-INF/includes/header.jsp"/>
<jsp:include page="/WEB-INF/includes/menu.jsp"/>

<div style="margin:20px;">
    <jsp:include page="${param.contenido}"/>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp"/>

</body>
</html>

