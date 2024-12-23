<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Family Budgets</title>
</head>
<body>
<h1>Family Budgets</h1>

<form action="${pageContext.request.contextPath}/family-budgets" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="text" id="familyId" name="familyId" required>
    <label for="balance">Balance:</label>
    <input type="text" id="balance" name="balance" required>
    <button type="submit">Add Family Budget</button>
</form>

<p><strong>Total family budgets count:</strong> ${familyBudgets.size()}</p>

<h2>Family Budget List</h2>
<ul>
    <c:forEach var="familyBudget" items="${familyBudgets}">
        <li>
            <a href="${pageContext.request.contextPath}/family-budgets/${familyBudget.familyId}">${familyBudget.familyId}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>