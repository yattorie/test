<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Budgets</title>
</head>
<body>
<h1>Category Budgets</h1>

<form action="${pageContext.request.contextPath}/category-budgets" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="number" id="familyId" name="familyId" required>
    <label for="categoryId">Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" required>
    <label for="budgetAmount">Budget Amount:</label>
    <input type="text" id="budgetAmount" name="budgetAmount" required>
    <label for="spentAmount">Spent Amount:</label>
    <input type="text" id="spentAmount" name="spentAmount" required>
    <button type="submit">Add Category Budget</button>
</form>

<p><strong>Total category budgets count:</strong> ${categoryBudgets.size()}</p>

<h2>Category Budget List</h2>
<ul>
    <c:forEach var="categoryBudget" items="${categoryBudgets}">
        <li>
            <a href="${pageContext.request.contextPath}/category-budgets/${categoryBudget.categoryBudgetId}">${categoryBudget.categoryId}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>