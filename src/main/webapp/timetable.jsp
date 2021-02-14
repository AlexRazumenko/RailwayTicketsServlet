<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 11.02.2021
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search train</title>
</head>
<body>
<h2>Search train</h2>

<h3>Search trains from station:</h3>
<form action="${pageContext.request.contextPath}/user/trainsByOneStation" method="get">
    <select name="departStationId">
    <c:forEach items="${allStations}" var="station">
        <option value="${station.id}"><c:out value="${station.name}"/></option>
    </c:forEach>
        <input type="date" value=""
    </select>
    <input type="submit" value="Search">
</form>

<h3>Search trains between stations:</h3>
<form action="${pageContext.request.contextPath}/user/trainsByTwoStations" method="get">
    Departure station:
    <select name="departStationId">
        <c:forEach items="${allStations}" var="departStation">
            <option value="${departStation.id}"><c:out value="${departStation.name}"/></option>
        </c:forEach>
    </select>

    Arrival station:
        <select name="arriveStationId">
            <c:forEach items="${allStations}" var="arriveStation">
                <option value="${arriveStation.id}"><c:out value="${arriveStation.name}"/></option>
            </c:forEach>
        </select>
    <input type="submit" value="Search">
</form>

<c:if test="${trains ne null}">
    <h3>Search result:</h3>



    <table border="1" style="border-collapse: collapse">
<tr>
    <td>ID:</td>
    <td>Number</td>
    <td>Departure Station</td>
    <td>Departure Time</td>
    <td>Arrival Station</td>
    <td>Arrival Time</td>
    <td>Ticket Price</td>
    <td>Tickets Booking</td>
</tr>

        <c:forEach items="${trains}" var="train">
            <tr>
                <td>${train.id}</td>
                <td>${train.number}</td>
                <td>${train.departStation.name}</td>
                <td>${train.departTime}</td>
                <td>${train.arriveStation.name}</td>
                <td>${train.arriveTime}</td>
                <td>${train.price}</td>
                <td>
            <form action="${pageContext.request.contextPath}/user/trainPage" method="post">
                <input type="hidden" value="${train.id}" name="trainId">
                    <input type="submit" value="Book ticket!">
            </form>
                </td>
            </tr>

        </c:forEach>
    </table>




</c:if>



</body>
</html>
