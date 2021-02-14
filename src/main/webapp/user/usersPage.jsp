<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 02.02.2021
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/JSP/Page" prefix="jsp" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<h2>Users page</h2>

<c:set var="aaa" value="${param.allUsers}" scope="session"/>
<c:forEach items="${aaa}" var="prop">
    <c:out value="${prop.email}"/><br>
</c:forEach>

<table border="1" style="border-collapse: collapse">
    <tr>
        <td>ID</td>
        <td>Email</td>
        <td>First Name</td>
        <td>Last Name</td>
    </tr>

    <c:forEach items="${allUsers}" var="user">
        <tr>
        <td><c:out value="${user.id}"/> </td>
        <td><c:out value="${user.email}"/> </td>
        <td><c:out value="${user.firstName}"/> </td>
        <td><c:out value="${user.lastName}"/> </td>
        </tr>
    </c:forEach>
</table>

    <p></p>
    <font color="#ffebcd"> Delete user:</font>
    <c:if test="${allUsers ne null}">

        <form action="/user/deleteUser" method="post" name="user">

            <select name="id">
                <c:forEach items="${allUsers}" var="user">
                    <option value="${user.id}"><c:out value="${user.email}"/></option>
                </c:forEach>
            </select>

            <input type="submit" value="Delete"/>
        </form>
    </c:if>
    <br>


</body>
</html>
