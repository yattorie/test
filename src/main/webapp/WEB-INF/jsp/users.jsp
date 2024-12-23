<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Users</title>
</head>
<body>
<h1>Users</h1>

<form action="${pageContext.request.contextPath}/users" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <button type="submit">Add User</button>
</form>

<p><strong>Total users count:</strong> ${users.size()}</p>

<h2>User List</h2>
<ul>
    <c:forEach var="user" items="${users}">
        <li>
            <a href="${pageContext.request.contextPath}/users/${user.userId}">${user.username}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>