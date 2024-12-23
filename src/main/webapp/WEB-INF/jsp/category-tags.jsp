<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Tags</title>
</head>
<body>
<h1>Category Tags</h1>

<form action="${pageContext.request.contextPath}/category-tags" method="POST">
    <label for="categoryId">Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" required>
    <label for="tagId">Tag ID:</label>
    <input type="number" id="tagId" name="tagId" required>
    <button type="submit">Add Category Tag</button>
</form>

<p><strong>Total category tags count:</strong> ${categoryTags.size()}</p>

<h2>Category Tag List</h2>
<ul>
    <c:forEach var="categoryTag" items="${categoryTags}">
        <li>
            <a href="${pageContext.request.contextPath}/category-tags/${categoryTag.categoryId}">${categoryTag.tagId}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>