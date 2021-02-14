
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 04.02.2021
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><c:out value="${mainMessage}" default="Stations management page"/></h2>


<h3><c:out value="Input station name:"/></h3>

<form method="post" action="${pageContext.request.contextPath}/admin/addStation">
<input title="name" type="text" name="name"/>
    <input type="submit" value="Add!">
</form>


<h3>Delete station:</h3>
<c:if test="${allStations ne null}">

    <form action="/admin/deleteStation" method="post" name="deleteStationForm">

        <select name="id">
            <c:forEach items="${allStations}" var="station">
                <option value="${station.id}"><c:out value="${station.name}"/></option>
            </c:forEach>
        </select>

        <input type="submit" value="Delete"/>
    </form>
</c:if>

<br>
<h3>All stations:</h3>


<table border="1" style="border-collapse: collapse">
    <th style="justify-content: center">All stations:</th>
    <tr>
        <td>ID</td>
        <td>Name</td>
    </tr>

    <c:forEach items="${allStations}" var="station">
        <tr>
            <td><c:out value="${station.id}"/> </td>
            <td><c:out value="${station.name}"/> </td>
        </tr>
    </c:forEach>
</table>

<br>



</body>
</html>
