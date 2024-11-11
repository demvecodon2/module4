<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2>Customer Details</h2>

    <c:if test="${not empty customer}">
        <table class="table table-bordered">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <td>${customer.id}</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>Name</th>
                <td>${customer.name}</td>
            </tr>
            <tr>
                <th>Email</th>
                <td>${customer.email}</td>
            </tr>
            <tr>
                <th>Address</th>
                <td>${customer.address}</td>
            </tr>
            </tbody>
        </table>

        <a href="/customers" class="btn btn-primary">Back to Customer List</a>
    </c:if>

    <c:if test="${empty customer}">
        <p class="alert alert-warning">Customer not found!</p>
        <a href="/customers" class="btn btn-secondary">Back to Customer List</a>
    </c:if>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
