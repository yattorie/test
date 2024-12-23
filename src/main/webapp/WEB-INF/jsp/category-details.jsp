<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Details</title>
</head>
<body>
<h1>Category Details</h1>

<p><strong>Category ID:</strong> ${category.categoryId}</p>
<p><strong>Category Name:</strong> ${category.categoryName}</p>
<p><strong>Type:</strong> ${category.type}</p>
<p><strong>Parent Category ID:</strong> ${category.parentCategoryId}</p>
<p><strong>Created At:</strong> ${category.createdAt}</p>
<p><strong>Updated At:</strong> ${category.updatedAt}</p>
<p><strong>Created By:</strong> ${category.createdBy}</p>

<form action="${pageContext.request.contextPath}/categories/${category.categoryId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="categoryName">Update Category Name:</label>
    <input type="text" id="categoryName" name="categoryName" value="${category.categoryName}" required>
    <label for="type">Update Type:</label>
    <input type="text" id="type" name="type" value="${category.type}" required>
    <label for="parentCategoryId">Update Parent Category ID:</label>
    <input type="number" id="parentCategoryId" name="parentCategoryId" value="${category.parentCategoryId}">
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/categories/${category.categoryId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Category</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/categories">Back to Categories List</a>
</body>
</html>