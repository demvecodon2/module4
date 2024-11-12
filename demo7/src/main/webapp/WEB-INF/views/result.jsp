<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Selected Spices</title>
</head>
<body>
<h1>Your Selected Spices</h1>
<c:if test="${not empty selectedSpices}">
    <ul>
        <c:forEach var="spice" items="${selectedSpices}">
            <li>${spice}</li>
        </c:forEach>
    </ul>
</c:if>
<c:if test="${empty selectedSpices}">
    <p>You have not selected any spices.</p>
</c:if>
<br>
<a href="/selectSpices">Go back to select spices</a>
</body>
</html>
