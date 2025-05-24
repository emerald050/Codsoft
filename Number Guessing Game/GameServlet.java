import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
public class GameServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("number") == null) {
            int number = (int) (Math.random() * 100) + 1;
            session.setAttribute("number", number);
            session.setAttribute("attempts", 0);
            if (session.getAttribute("score") == null) {
                session.setAttribute("score", 0);
            }
        }
        req.getRequestDispatcher("Game.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        Integer number = (Integer) session.getAttribute("number");
        Integer attempts = (Integer) session.getAttribute("attempts");
        Integer score = (Integer) session.getAttribute("score");

        if (number == null || attempts == null || score == null) {
            resp.sendRedirect("GameServlet");
            return;
        }

        String guessParam = req.getParameter("guess");
        String message = "";
        boolean gameOver = false;
        boolean won = false;

        int guess = 0;
        try {
            guess = Integer.parseInt(guessParam.trim());
            if (guess < 1 || guess > 100) {
                message = "Your guess must be between 1 and 100.";
                req.setAttribute("message", message);
                req.setAttribute("gameOver", false);
                req.getRequestDispatcher("Game.jsp").forward(req, resp);
                return;
            }
        } catch (NumberFormatException e) {
            message = "Please enter a valid number.";
            req.setAttribute("message", message);
            req.setAttribute("gameOver", false);
            req.getRequestDispatcher("Game.jsp").forward(req, resp);
            return;
        }

        attempts++;
        if (guess == number) {
            message = "🎉 Correct! The Pokémon is revealed!";
            score++;
            gameOver = true;
            won = true;
        } else if (guess < number) {
            message = "🔻 Too low! Attempts left: " + (7 - attempts);
        } else {
            message = "🔺 Too high! Attempts left: " + (7 - attempts);
        }

        if (attempts >= 7 && !won) {
            message = "❌ Out of attempts! The Pokémon was number " + number + ".";
            gameOver = true;
        }

        session.setAttribute("attempts", gameOver ? 0 : attempts);
        session.setAttribute("number", gameOver ? (int) (Math.random() * 100) + 1 : number);
        session.setAttribute("score", score);

        req.setAttribute("message", message);
        req.setAttribute("gameOver", gameOver);
        req.setAttribute("won", won);
        req.setAttribute("score", score);

        req.getRequestDispatcher("Game.jsp").forward(req, resp);
    }
}
