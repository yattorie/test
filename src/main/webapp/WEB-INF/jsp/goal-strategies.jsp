<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Goal Strategies</title>
</head>
<body>
<h1>Goal Strategies</h1>

<form action="${pageContext.request.contextPath}/goal-strategies" method="POST">
    <label for="goalId">Goal ID:</label>
    <input type="number" id="goalId" name="goalId" required>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <label for="step">Step:</label>
    <input type="text" id="step" name="step" required>
    <button type="submit">Add Goal Strategy</button>
</form>

<p><strong>Total goal strategies count:</strong> ${goalStrategies.size()}</p>

<h2>Goal Strategy List</h2>
<ul>
    <c:forEach var="goalStrategy" items="${goalStrategies}">
        <li>
            <a href="${pageContext.request.contextPath}/goal-strategies/${goalStrategy.strategyId}">${goalStrategy.description}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>