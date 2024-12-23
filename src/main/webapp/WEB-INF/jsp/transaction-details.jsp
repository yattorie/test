<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Transaction Details</title>
</head>
<body>
<h1>Transaction Details</h1>

<p><strong>ID:</strong> ${transaction.transactionId}</p>
<p><strong>Family ID:</strong> ${transaction.familyId}</p>
<p><strong>User ID:</strong> ${transaction.userId}</p>
<p><strong>Category ID:</strong> ${transaction.categoryId}</p>
<p><strong>Amount:</strong> ${transaction.amount}</p>
<p><strong>Type:</strong> ${transaction.type}</p>
<p><strong>Description:</strong> ${transaction.description}</p>

<form action="${pageContext.request.contextPath}/transactions/${transaction.transactionId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="familyId">Update Family ID:</label>
    <input type="number" id="familyId" name="familyId" value="${transaction.familyId}" required>
    <label for="userId">Update User ID:</label>
    <input type="number" id="userId" name="userId" value="${transaction.userId}" required>
    <label for="categoryId">Update Category ID:</label>
    <input type="number" id="categoryId" name="categoryId" value="${transaction.categoryId}" required>
    <label for="amount">Update Amount:</label>
    <input type="text" id="amount" name="amount" value="${transaction.amount}" required>
    <label for="type">Update Type:</label>
    <input type="text" id="type" name="type" value="${transaction.type}" required>
    <label for="description">Update Description:</label>
    <input type="text" id="description" name="description" value="${transaction.description}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/transactions/${transaction.transactionId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Transaction</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/transactions">Back to Transactions List</a>
</body>
</html>