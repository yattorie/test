<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Tag Details</title>
</head>
<body>
<h1>Category Tag Details</h1>

<p><strong>Category ID:</strong> ${categoryTag.categoryId}</p>
<p><strong>Tag ID:</strong> ${categoryTag.tagId}</p>
<p><strong>Created At:</strong> ${categoryTag.createdAt}</p>

<form action="${pageContext.request.contextPath}/category-tags/${categoryTag.categoryId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="tagId">Update Tag ID:</label>
    <input type="number" id="tagId" name="tagId" value="${categoryTag.tagId}" required>
    <label for="createdAt">Update Created At:</label>
    <input type="text" id="createdAt" name="createdAt" value="${categoryTag.createdAt}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/category-tags/${categoryTag.categoryId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <label for="tagIdToDelete">Tag ID to Delete:</label>
    <input type="number" id="tagIdToDelete" name="tagId" required>
    <button type="submit">Delete Category Tag</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/category-tags">Back to Category Tags List</a>
</body>
</html>