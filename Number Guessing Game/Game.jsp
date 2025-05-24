<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Guess That Pokémon</title>
  <link rel="stylesheet" href="styles.css" />
</head>
<body>
  <div class="container">
    <h1>Guess That Numerion!</h1>
    <p>Guess a number between 1 and 100. You have 7 attempts per round.</p>

    <form method="post" action="GameServlet">
      <input type="text" name="guess" required placeholder="Enter your guess"
             inputmode="numeric" pattern="[0-9]{1,3}" class="guess-input" />
      <button type="submit">Guess</button>
    </form>
    <%
      String message = (String) request.getAttribute("message");
      Integer attempts = (Integer) session.getAttribute("attempts");
      Integer score = (Integer) session.getAttribute("score");
      Boolean gameOver = (Boolean) request.getAttribute("gameOver");
      if (message != null) {
    %>
      <div class="result-message">
        <p><strong><%= message %></strong></p>
        <% if (attempts != null) { %>
          <p>Attempts used: <%= attempts %> / 7</p>
        <% } %>
        <% if (score != null) { %>
          <p>Your score: <%= score %></p>
        <% } %>
        <% if (gameOver != null && gameOver) { %>
          <form method="get" action="GameServlet">
            <button type="submit">Start New Round</button>
          </form>
        <% } %>
      </div>
    <% } %>
  </div>
  <script>
    document.querySelector('.guess-input').addEventListener('input', function () {
      this.value = this.value.replace(/[^0-9]/g, '');
    });
  </script>
</body>
</html>
