<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Email Configuration</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2>Email Configuration</h2>
    <c:if test="${not empty message}">
        <div class="alert alert-success" role="alert">
                ${message}
        </div>
    </c:if>

    <!-- Form cấu hình email -->
    <form:form modelAttribute="mailConfig" method="post" action="${pageContext.request.contextPath}/saveConfig">

        <!-- Các trường thông tin trong form -->
        <div class="form-group">
            <label for="language">Language:</label>
            <form:select path="language" class="form-control">
                <form:option value="English" label="English"/>
                <form:option value="Vietnamese" label="Vietnamese"/>
                <form:option value="Japanese" label="Japanese"/>
                <form:option value="Chinese" label="Chinese"/>
            </form:select>
        </div>

        <div class="form-group">
            <label for="pageSize">Page Size:</label>
            <form:select path="pageSize" class="form-control">
                <form:option value="5" label="5 emails per page"/>
                <form:option value="10" label="10 emails per page"/>
                <form:option value="15" label="15 emails per page"/>
                <form:option value="25" label="25 emails per page"/>
                <form:option value="50" label="50 emails per page"/>
                <form:option value="100" label="100 emails per page"/>
            </form:select>
        </div>

        <div class="form-check">
            <form:checkbox path="spamsFilter" class="form-check-input"/>
                <%--@declare id="spamsfilter"--%><label class="form-check-label" for="spamsFilter">Enable Spam
            Filter</label>
        </div>

        <div class="form-group">
            <label for="signature">Signature:</label>
            <form:textarea path="signature" class="form-control" rows="4"></form:textarea>
        </div>

        <!-- Nút Save và Update -->
        <button type="submit" class="btn btn-primary" name="action" value="save">Save Configuration</button>
        <button type="submit" class="btn btn-secondary" name="action" value="update">Update Configuration</button>
    </form:form>
</div>

</body>
</html>
