<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Goal Details</title>
</head>
<body>
<h1>Goal Details</h1>

<p><strong>ID:</strong> ${goal.goalId}</p>
<p><strong>Family ID:</strong> ${goal.familyId}</p>
<p><strong>Goal Name:</strong> ${goal.goalName}</p>
<p><strong>Description:</strong> ${goal.description}</p>
<p><strong>Target Amount:</strong> ${goal.targetAmount}</p>
<p><strong>Due Date:</strong> ${goal.dueDate}</p>

<form action="${pageContext.request.contextPath}/goals/${goal.goalId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="familyId">Update Family ID:</label>
    <input type="number" id="familyId" name="familyId" value="${goal.familyId}" required>
    <label for="goalName">Update Goal Name:</label>
    <input type="text" id="goalName" name="goalName" value="${goal.goalName}" required>
    <label for="description">Update Description:</label>
    <input type="text" id="description" name="description" value="${goal.description}" required>
    <label for="targetAmount">Update Target Amount:</label>
    <input type="text" id="targetAmount" name="targetAmount" value="${goal.targetAmount}" required>
    <label for="dueDate">Update Due Date:</label>
    <input type="date" id="dueDate" name="dueDate" value="${goal.dueDate}" required>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/goals/${goal.goalId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Goal</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/goals">Back to Goals List</a>
</body>
</html>