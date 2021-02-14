
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 07.02.2021
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hour" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2><c:out value="${mainMessage}" default="Trains management page"/></h2>

<%--<jsp:useBean id="arriveStation" class="ua.alex.railway.tickets.entity.Station" scope="session"/>--%>
<%--<jsp:useBean id="departStation" class="ua.alex.railway.tickets.entity.Station" scope="session"/>--%>
<%--<jsp:useBean id="newTrainDto" class="ua.alex.railway.tickets.dto.TrainDTO" scope="session"/>--%>


<%--<form method="post" action="${pageContext.request.contextPath}/admin/addTrain" id="addTrainForm" name="">--%>



    <%--Departure station:<br>--%>
    <%--<select name="${newTrainDto.departStation}" form="addTrainForm">--%>
        <%--<c:forEach items="${allStations}" var="departStation1">--%>
            <%--<option value="${departStation1}"><c:out value="${departStation1.name}"/></option>--%>
        <%--</c:forEach>--%>
    <%--</select><br>--%>

    <%--<input type="submit" value="Add!"><br>--%>
<%--</form>--%>

<%--Departure station:<br>--%>
<%--<select name="${newTrainDto.departStation}" title="departStation" form="addTrainForm">--%>
    <%--<c:forEach items="${allStations}" var="departStation">--%>
        <%--<option value="${departStation}"><c:out value="${departStation.name}"/></option>--%>
    <%--</c:forEach>--%>
<%--</select><br>--%>

<%--Arrival station:<br>--%>
<%--<select name="${newTrainDto.arriveStation}" title="arriveStation" form="addTrainForm" >--%>
    <%--<c:forEach items="${allStations}" var="arriveStation">--%>
        <%--<option value="${arriveStation}"><c:out value="${arriveStation.name}"/></option>--%>
    <%--</c:forEach>--%>
<%--</select><br>--%>

<form method="post" action="${pageContext.request.contextPath}/admin/addTrain" id="addTrainForm">
    Number: <input title="number" name="number"/><br>

    Departure station:<br>
    <select name="departStationId" title="departStationId" form="addTrainForm">
        <c:forEach items="${allStations}" var="departStation">
            <option value="${departStation.id}"><c:out value="${departStation.name}"/></option>
        </c:forEach>
    </select><br>

    Arrival station:<br>
    <select name="arriveStationId" title="arriveStationId" form="addTrainForm" >
        <c:forEach items="${allStations}" var="arriveStation">
            <option value="${arriveStation.id}"><c:out value="${arriveStation.name}"/></option>
        </c:forEach>
    </select><br>

    <%--<c:set var="departStation" value="${departStation}" scope="session"/>--%>
    <%--<c:set var="arriveStation" value="${arriveStation}" scope="session"/>--%>
    Departure hour: <input type="text" title="departHour" name="departHour" min="0" max="23"/><br>
    Departure minute:<input type="text" title="departMinute" name="departMinute" min="0" max="59"/><br>
    Arrival hour:<input type="text" title="arriveHour" name="arriveHour" min="0" max="23"/><br>
    Arrival minute:<input type="text" title="arriveMinute" name="arriveMinute" min="0" max="59"/><br>
    Ticket price:<input title="price" type="text" name="price"/><br>
    <input type="submit" value="Add!"><br>
</form>

<h3>Delete Train:</h3>
<c:if test="${allTrains ne null}">

    <form action="/admin/deleteTrain" method="post" name="deleteTrainForm">

        <select name="id" title="train" >
            <c:forEach items="${allTrains}" var="train">
                <option value="${train.id}">
                    <c:out value="${train.number} ${train.departStation.name} - ${train.arriveStation.name}"/></option>
            </c:forEach>
        </select>

        <input type="submit" value="Delete"/>
    </form>
</c:if>

<br>
<h3>All stations:</h3>


<table border="1" style="border-collapse: collapse">
    <th style="justify-content: center">All trains:</th>
    <tr>
        <td>ID</td>
        <td>Number</td>
        <td>Depart Station</td>
        <td>Depart Time</td>
        <td>Arrive Station</td>
        <td>Arrive Time</td>
        <td>Ticket Price</td>
    </tr>

    <c:forEach items="${allTrains}" var="train">
        <tr>
            <td><c:out value="${train.id}"/> </td>
            <td><c:out value="${train.number}"/> </td>
            <td><c:out value="${train.departStation.name}"/> </td>
            <td><c:out value="${train.departTime}"/> </td>
            <td><c:out value="${train.arriveStation.name}"/> </td>
            <td><c:out value="${train.arriveTime}"/> </td>
            <td><c:out value="${train.price}"/> </td>
        </tr>
    </c:forEach>
</table>

<br>



</body>
</html>
