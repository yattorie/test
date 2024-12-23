<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profiles</title>
</head>
<body>
<h1>User Profiles</h1>

<form action="${pageContext.request.contextPath}/user-profiles" method="POST">
    <label for="userId">User ID:</label>
    <input type="number" id="userId" name="userId" required>
    <label for="dateOfBirth">Date of Birth:</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" required>
    <label for="familyStatus">Family Status:</label>
    <input type="text" id="familyStatus" name="familyStatus" required>
    <label for="gender">Gender:</label>
    <input type="text" id="gender" name="gender" required>
    <button type="submit">Add User Profile</button>
</form>

<p><strong>Total user profiles count:</strong> ${userProfiles.size()}</p>

<h2>User Profile List</h2>
<ul>
    <c:forEach var="userProfile" items="${userProfiles}">
        <li>
            <a href="${pageContext.request.contextPath}/user-profiles/${userProfile.userProfileId}">${userProfile.userId}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>