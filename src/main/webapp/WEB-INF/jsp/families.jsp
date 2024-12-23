<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Families</title>
</head>
<body>
<h1>Families</h1>

<form action="${pageContext.request.contextPath}/families" method="POST">
    <label for="familyName">Family Name:</label>
    <input type="text" id="familyName" name="familyName" required>
    <label for="familyDescription">Family Description:</label>
    <input type="text" id="familyDescription" name="familyDescription" required>
    <button type="submit">Add Family</button>
</form>

<p><strong>Total families count:</strong> ${families.size()}</p>

<h2>Family List</h2>
<ul>
    <c:forEach var="family" items="${families}">
        <li>
            <a href="${pageContext.request.contextPath}/families/${family.familyId}">${family.familyName}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>