<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Goal Strategy Details</title>
</head>
<body>
<h1>Goal Strategy Details</h1>

<p><strong>ID:</strong> ${goalStrategy.strategyId}</p>
<p><strong>Goal ID:</strong> ${goalStrategy.goalId}</p>
<p><strong>Description:</strong> ${goalStrategy.description}</p>
<p><strong>Step:</strong> ${goalStrategy.step}</p>
<p><strong>Created At:</strong> ${goalStrategy.createdAt}</p>

<form action="${pageContext.request.contextPath}/goal-strategies/${goalStrategy.strategyId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="description">Update Description:</label>
    <input type="text" id="description" name="description" value="${goalStrategy.description}" required>
    <label for="step">Update Step:</label>
    <input type="text" id="step" name="step" value="${goalStrategy.step}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/goal-strategies/${goalStrategy.strategyId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Goal Strategy</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/goal-strategies">Back to Goal Strategies List</a>
</body>
</html>