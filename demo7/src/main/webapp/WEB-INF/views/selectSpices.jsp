<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Spices</title>
</head>
<body>
<h1>Select Your Spices</h1>
<form action="/selectSpices" method="post">
    <c:forEach var="spice" items="${spicesList}">
        <div>
            <input type="checkbox" name="spices" value="${spice}"> ${spice}
        </div>
    </c:forEach>
    <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
