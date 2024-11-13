<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="styles.css">  <!-- Optional: Add your custom styles -->
</head>
<body>
<div class="container">
    <h2>Login to your account</h2>

    <!-- Form for Login -->
    <form:form method="POST" action="/login" modelAttribute="login">
        <table>
            <tr>
                <td><form:label path="account">Account:</form:label></td>
                <td><form:input path="account" id="account" required="true" /></td>
            </tr>
            <tr>
                <td><form:label path="password">Password:</form:label></td>
                <td><form:input path="password" type="password" id="password" required="true" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Login" />
                </td>
            </tr>
        </table>
    </form:form>

    <!-- Displaying error message if login fails (optional) -->
    <div id="errorMessage" style="color: red;">
        <c:if test="${not empty errorMessage}">
            ${errorMessage}
        </c:if>
    </div>
</div>

</body>
</html>
