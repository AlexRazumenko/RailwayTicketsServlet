<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 03.02.2021
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register page</title>
</head>
<body>

<c:out value="Input your data:"/>

<form method="post" action="${pageContext.request.contextPath}/registration">
    e-mail: <input title="email" type="text" name="email"/><br>
    password: <input title="pass" type="password" name="pass"/>
    first name: <input title="firstname" type="text" name="firstName"/>
    last name: <input title="lastname" type="text" name="lastName"/>
    <input type="submit" value="Submit!">

</form>



</body>
</html>
