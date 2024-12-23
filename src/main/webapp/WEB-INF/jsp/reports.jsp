<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reports</title>
</head>
<body>
<h1>Reports</h1>

<form action="${pageContext.request.contextPath}/reports" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="text" id="familyId" name="familyId" required>
    <label for="reportName">Report Name:</label>
    <input type="text" id="reportName" name="reportName" required>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <button type="submit">Add Report</button>
</form>

<h2>Report List</h2>
<ul>
    <c:forEach var="report" items="${reports}">
        <li>
            <a href="${pageContext.request.contextPath}/reports/${report.reportId}">${report.reportName}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
