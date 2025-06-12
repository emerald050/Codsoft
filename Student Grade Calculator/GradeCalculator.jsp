<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>Student Grade Calculator</title>
  <link rel="stylesheet" href="styles1.css"/>
</head>
<body>
  <div class="container">
    <h1>Student Grade Calculator</h1>
    <form method="post" action="GradeServlet">
      <input type="number" name="subject1" min="0" max="100" required placeholder="Subject 1 marks"/>
      <input type="number" name="subject2" min="0" max="100" required placeholder="Subject 2 marks"/>
      <input type="number" name="subject3" min="0" max="100" required placeholder="Subject 3 marks"/>
      <button type="submit">Calculate Grade</button>
    </form>
  </div>
</body>
</html>
