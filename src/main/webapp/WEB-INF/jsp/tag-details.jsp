<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tag Details</title>
</head>
<body>
<h1>Tag Details</h1>

<p><strong>ID:</strong> ${tag.tagId}</p>
<p><strong>Tag Name:</strong> ${tag.tagName}</p>
<p><strong>Created At:</strong> ${tag.createdAt}</p>

<form action="${pageContext.request.contextPath}/tags/${tag.tagId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="name">Update Tag Name:</label>
    <input type="text" id="name" name="name" value="${tag.tagName}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/tags/${tag.tagId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Tag</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/tags">Back to Tags List</a>
</body>
</html>