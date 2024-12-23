<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Members</title>
</head>
<body>
<h1>Family Members</h1>

<form action="${pageContext.request.contextPath}/family-members/" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="number" id="familyId" name="familyId" required>
    <label for="userId">User ID:</label>
    <input type="number" id="userId" name="userId" required>
    <label for="role">Role:</label>
    <input type="text" id="role" name="role" required>
    <button type="submit">Add Family Member</button>
</form>

<p><strong>Total family members count:</strong> ${familyMembers.size()}</p>

<h2>Family Member List</h2>
<ul>
    <c:forEach var="member" items="${familyMembers}">
        <li>
            <a href="${pageContext.request.contextPath}/family-members/${member.memberId}">${member.role} - ${member.userId}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
