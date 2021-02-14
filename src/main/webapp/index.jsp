<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%--<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/JSP/Page" prefix="jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<body>
<h2>Index page</h2>

<p><c:out value="${mainMessage}"/></p>
<p><c:out value="${roleMessage}"/></p>
<%--<c:out value="${message}"/>--%>
<%--<a href="<c:out value="/user/userPage"/>">User Page</a>--%>

<a href="<c:url value="/loginPage"/>">Log in</a><br><br>

<a href="<c:url value="/user/userPage"/>">User page</a><br><br>
<a href="<c:url value="/user/users"/>">Users page</a><br><br>
<a href="<c:url value="/user/registrationPage"/>">Register new user</a><br><br>
<a href="<c:url value="/timetable"/>">Search trains</a><br><br>

<%--<a href="${pageContext.request.contextPath}/user/users">User page</a>--%>
<%--<a href="/login.jsp">Log in</a>--%>
<br><br>
<a href="<c:url value="/admin/stations"/>">Stations page</a><br><br>
<a href="<c:url value="/admin/trains"/>">Trains page</a><br><br>




</body>
</html>
