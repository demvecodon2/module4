
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Email Validation</title>
</head>
<body>

<h2>Email Validation</h2>

<c:if test="${not empty errorMessage}">
    <p style="color: red;">${errorMessage}</p>
</c:if>

<form action="${pageContext.request.contextPath}/validate" method="POST">
    <label for="email">Enter your email:</label>
    <input type="text" id="email" name="email" required />
    <button type="submit">Validate</button>
</form>

</body>
</html>
