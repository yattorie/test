<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category Budget Details</title>
</head>
<body>
<h1>Category Budget Details</h1>

<p><strong>ID:</strong> ${categoryBudget.categoryBudgetId}</p>
<p><strong>Family ID:</strong> ${categoryBudget.familyId}</p>
<p><strong>Category ID:</strong> ${categoryBudget.categoryId}</p>
<p><strong>Budget Amount:</strong> ${categoryBudget.budgetAmount}</p>
<p><strong>Spent Amount:</strong> ${categoryBudget.spentAmount}</p>

<form action="${pageContext.request.contextPath}/category-budgets/${categoryBudget.categoryBudgetId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="spentAmount">Update Spent Amount:</label>
    <input type="text" id="spentAmount" name="spentAmount" value="${categoryBudget.spentAmount}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/category-budgets/${categoryBudget.categoryBudgetId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Category Budget</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/category-budgets">Back to Category Budgets List</a>
</body>
</html>