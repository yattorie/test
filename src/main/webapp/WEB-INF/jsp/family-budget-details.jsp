<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Budget Details</title>
</head>
<body>
<h1>Family Budget Details</h1>

<p><strong>Family ID:</strong> ${familyBudget.familyId}</p>
<p><strong>Balance:</strong> ${familyBudget.balance}</p>

<form action="${pageContext.request.contextPath}/family-budgets/${familyBudget.familyId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="balance">Update Balance:</label>
    <input type="text" id="balance" name="balance" value="${familyBudget.balance}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/family-budgets/${familyBudget.familyId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Family Budget</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/family-budgets">Back to Family Budgets List</a>
</body>
</html>