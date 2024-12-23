<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Goals</title>
</head>
<body>
<h1>Goals</h1>

<form action="${pageContext.request.contextPath}/goals" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="number" id="familyId" name="familyId" required>
    <label for="goalName">Goal Name:</label>
    <input type="text" id="goalName" name="goalName" required>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <label for="targetAmount">Target Amount:</label>
    <input type="text" id="targetAmount" name="targetAmount" required>
    <label for="dueDate">Due Date:</label>
    <input type="date" id="dueDate" name="dueDate" required>
    <button type="submit">Add Goal</button>
</form>

<p><strong>Total goals count:</strong> ${goals.size()}</p>

<h2>Goal List</h2>
<ul>
    <c:forEach var="goal" items="${goals}">
        <li>
            <a href="${pageContext.request.contextPath}/goals/${goal.goalId}">${goal.goalName}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>