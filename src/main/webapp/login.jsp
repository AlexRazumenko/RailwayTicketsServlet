<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 09.02.2021
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:out value="${loginPageMessage}"/>
<form name="login" action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="login" title="login"/>
    <input type="password" name="password" title="password"/>
<input type="submit" value="Log in"/>
</form>


</body>
</html>
