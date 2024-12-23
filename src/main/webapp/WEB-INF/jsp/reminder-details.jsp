<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reminder Details</title>
</head>
<body>
<h1>Reminder Details</h1>

<p><strong>ID:</strong> ${reminder.reminderId}</p>
<p><strong>Family ID:</strong> ${reminder.familyId}</p>
<p><strong>Description:</strong> ${reminder.description}</p>
<p><strong>Reminder Date:</strong> ${reminder.reminderDate}</p>
<p><strong>Status:</strong> ${reminder.status}</p>

<form action="${pageContext.request.contextPath}/reminders/${reminder.reminderId}" method="POST">
    <input type="hidden" name="_method" value="PUT">
    <label for="familyId">Update Family ID:</label>
    <input type="number" id="familyId" name="familyId" value="${reminder.familyId}" required>
    <label for="description">Update Description:</label>
    <input type="text" id="description" name="description" value="${reminder.description}" required>
    <label for="reminderDate">Update Reminder Date:</label>
    <input type="datetime-local" id="reminderDate" name="reminderDate" value="${reminder.reminderDate}" required>
    <label for="status">Update Status:</label>
    <select id="status" name="status" required>
        <option value="Pending" ${reminder.status == 'Pending' ? 'selected' : ''}>Pending</option>
        <option value="Completed" ${reminder.status == 'Completed' ? 'selected' : ''}>Completed</option>
    </select>
    <button type="submit">Update</button>
</form>

<form action="${pageContext.request.contextPath}/reminders/${reminder.reminderId}" method="POST">
    <input type="hidden" name="_method" value="DELETE">
    <button type="submit">Delete Reminder</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/reminders">Back to Reminders List</a>
</body>
</html>
