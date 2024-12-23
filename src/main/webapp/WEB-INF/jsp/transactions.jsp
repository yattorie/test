<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transactions</title>
</head>
<body>
<h1>Transactions</h1>

<form action="${pageContext.request.contextPath}/transactions" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="number" id="familyId" name="familyId" required>
    <label for="userId">User ID:</label>
    <input type="number" id="userId" name="userId" required>
    <label for="categoryId">Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" required>
    <label for="amount">Amount:</label>
    <input type="text" id="amount" name="amount" required>
    <label for="type">Type:</label>
    <input type="text" id="type" name="type" required>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <button type="submit">Add Transaction</button>
</form>

<p><strong>Total transactions count:</strong> ${transactions.size()}</p>

<h2>Transaction List</h2>
<ul>
    <c:forEach var="transaction" items="${transactions}">
        <li>
            <a href="${pageContext.request.contextPath}/transactions/${transaction.transactionId}">${transaction.description}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>