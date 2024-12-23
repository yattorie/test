<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tags</title>
</head>
<body>
<h1>Tags</h1>

<form action="${pageContext.request.contextPath}/tags" method="POST">
    <label for="name">Tag Name:</label>
    <input type="text" id="name" name="name" required>
    <button type="submit">Add Tag</button>
</form>

<p><strong>Total tags count:</strong> ${tags.size()}</p>

<h2>Tag List</h2>
<ul>
    <c:forEach var="tag" items="${tags}">
        <li>
            <a href="${pageContext.request.contextPath}/tags/${tag.tagId}">${tag.tagName}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>