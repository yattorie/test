<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Details</title>
</head>
<body>
<h1>Family Details</h1>

<p><strong>Family ID:</strong> ${family.familyId}</p>
<p><strong>Family Name:</strong> ${family.familyName}</p>
<p><strong>Family Description:</strong> ${family.familyDescription}</p>

<form action="${pageContext.request.contextPath}/families/${family.familyId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="familyDescription">Update Description:</label>
    <input type="text" id="familyDescription" name="familyDescription" value="${family.familyDescription}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/families/${family.familyId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Family</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/families">Back to Families List</a>
</body>
</html>