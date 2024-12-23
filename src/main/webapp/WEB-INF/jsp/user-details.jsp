<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Details</title>
</head>
<body>
<h1>User Details</h1>

<p><strong>ID:</strong> ${user.userId}</p>
<p><strong>Username:</strong> ${user.username}</p>
<p><strong>Email:</strong> ${user.email}</p>

<form action="${pageContext.request.contextPath}/users/${user.userId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="username">Update Username:</label>
    <input type="text" id="username" name="username" value="${user.username}" required>
    <label for="email">Update Email:</label>
    <input type="email" id="email" name="email" value="${user.email}" required>
    <label for="password">Update Password:</label>
    <input type="password" id="password" name="password" value="${user.password}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/users/${user.userId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete User</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/users">Back to Users List</a>
</body>
</html>