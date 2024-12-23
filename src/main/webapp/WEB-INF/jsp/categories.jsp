<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Categories</title>
</head>
<body>
<h1>Categories</h1>

<form action="${pageContext.request.contextPath}/categories" method="POST">
    <label for="categoryName">Category Name:</label>
    <input type="text" id="categoryName" name="categoryName" required>
    <label for="type">Type:</label>
    <input type="text" id="type" name="type" required>
    <label for="parentCategoryId">Parent Category ID:</label>
    <input type="number" id="parentCategoryId" name="parentCategoryId">
    <label for="createdBy">Created By:</label>
    <input type="number" id="createdBy" name="createdBy" required>
    <button type="submit">Add Category</button>
</form>

<p><strong>Total categories count:</strong> ${categories.size()}</p>

<h2>Category List</h2>
<ul>
    <c:forEach var="category" items="${categories}">
        <li>
            <a href="${pageContext.request.contextPath}/categories/${category.categoryId}">${category.categoryName}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>