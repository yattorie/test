<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Member Details</title>
</head>
<body>
<h1>Family Member Details</h1>

<p><strong>Member ID:</strong> ${familyMember.memberId}</p>
<p><strong>Family ID:</strong> ${familyMember.familyId}</p>
<p><strong>User ID:</strong> ${familyMember.userId}</p>
<p><strong>Role:</strong> ${familyMember.role}</p>

<form action="${pageContext.request.contextPath}/family-members/${familyMember.memberId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="familyId">Update Family ID:</label>
    <input type="number" id="familyId" name="familyId" value="${familyMember.familyId}" required>
    <label for="userId">Update User ID:</label>
    <input type="number" id="userId" name="userId" value="${familyMember.userId}" required>
    <label for="role">Update Role:</label>
    <input type="text" id="role" name="role" value="${familyMember.role}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/family-members/${familyMember.memberId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete</button>
</form>

</body>
</html>
