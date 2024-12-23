<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reminders</title>
</head>
<body>
<h1>Reminders</h1>

<form action="${pageContext.request.contextPath}/reminders" method="POST">
    <label for="familyId">Family ID:</label>
    <input type="number" id="familyId" name="familyId" required>
    <label for="description">Description:</label>
    <input type="text" id="description" name="description" required>
    <label for="reminderDate">Reminder Date:</label>
    <input type="datetime-local" id="reminderDate" name="reminderDate" required>
    <label for="status">Status:</label>
    <select id="status" name="status" required>
        <option value="Pending">Pending</option>
        <option value="Completed">Completed</option>
    </select>
    <button type="submit">Add Reminder</button>
</form>

<p><strong>Total reminders count:</strong> ${reminders.size()}</p>

<h2>Reminder List</h2>
<ul>
    <c:forEach var="reminder" items="${reminders}">
        <li>
            <a href="${pageContext.request.contextPath}/reminders/${reminder.reminderId}">
                    ${reminder.description} (Due: ${reminder.reminderDate})
            </a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
