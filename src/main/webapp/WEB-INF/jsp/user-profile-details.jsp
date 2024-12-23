<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile Details</title>
</head>
<body>
<h1>User Profile Details</h1>

<p><strong>ID:</strong> ${userProfile.userProfileId}</p>
<p><strong>User ID:</strong> ${userProfile.userId}</p>
<p><strong>Date of Birth:</strong> ${userProfile.dateOfBirth}</p>
<p><strong>Family Status:</strong> ${userProfile.familyStatus}</p>
<p><strong>Gender:</strong> ${userProfile.gender}</p>

<form action="${pageContext.request.contextPath}/user-profiles/${userProfile.userProfileId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="userId">Update User ID:</label>
    <input type="number" id="userId" name="userId" value="${userProfile.userId}" required>
    <label for="dateOfBirth">Update Date of Birth:</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" value="${userProfile.dateOfBirth}" required>
    <label for="familyStatus">Update Family Status:</label>
    <input type="text" id="familyStatus" name="familyStatus" value="${userProfile.familyStatus}" required>
    <label for="gender">Update Gender:</label>
    <input type="text" id="gender" name="gender" value="${userProfile.gender}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/user-profiles/${userProfile.userProfileId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete User Profile</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/user-profiles">Back to User Profiles List</a>
</body>
</html>