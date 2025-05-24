<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Grade Results</title>
  <link rel="stylesheet" href="styles1.css"/>
</head>
<body>
  <div class="result-container">
    <h1>Your Results</h1>
    <p>Total Marks: <strong><%= request.getAttribute("total") %> / 300</strong></p>
    <p>Average Percentage: <strong><%= String.format("%.2f", request.getAttribute("average")) %> %</strong></p>
    <p>Grade: <strong><%= request.getAttribute("grade") %></strong></p>
    <form method="get" action="GradeServlet">
      <button type="submit">Calculate Again</button>
    </form>
  </div>
</body>
</html>
