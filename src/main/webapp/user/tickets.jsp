<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 09.02.2021
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tickets booking page</title>
</head>
<body>
<h2>Tickets booking page</h2>

<h2>My tickets</h2>

<table border="1" style="border-collapse: collapse">
    <c:forEach items="${myTickets}" var="ticket">
        <tr><td>
            <c:out value="ID: ${ticket.id} Train ${ticket.trainNumber} Date: ${ticket.departureDate}"/><br>
            <c:out value="Seat: ${ticket.place} Price: ${ticket.price}, "/>
            <c:out value="Departure: ${ticket.departStationName} ${ticket.departTime}, "/>
            <c:out value="Arrival: ${ticket.arriveStationName} ${ticket.arriveTime} "/><br>
            <c:out value="Passenger ${ticket.passengerFirstName} ${ticket.passengerFirstName}
                                (email: ${ticket.email})"/><br>



        </td></tr>

    </c:forEach>
</table>



</body>
</html>
