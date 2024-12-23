<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Report Details</title>
</head>
<body>
<h1>Report Details</h1>

<p><strong>ID:</strong> ${report.reportId}</p>
<p><strong>Family ID:</strong> ${report.familyId}</p>
<p><strong>Report Name:</strong> ${report.reportName}</p>
<p><strong>Description:</strong> ${report.description}</p>

<form action="${pageContext.request.contextPath}/reports/${report.reportId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="reportName">Update Report Name:</label>
    <input type="text" id="reportName" name="reportName" value="${report.reportName}" required>
    <label for="description">Update Description:</label>
    <input type="text" id="description" name="description" value="${report.description}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/reports/${report.reportId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Report</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/reports">Back to Reports List</a>
</body>
</html>
