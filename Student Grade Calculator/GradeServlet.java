import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
public class GradeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("GradeCalculator.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalSubjects = 3;
        int totalMarks = 0;
        String error = null;
        for (int i = 1; i <= totalSubjects; i++) {
            String param = req.getParameter("subject" + i);
            try {
                int mark = Integer.parseInt(param);
                if (mark < 0 || mark > 100) {
                    error = "Each mark must be between 0 and 100.";
                    break;
                }
                totalMarks += mark;
            } catch (NumberFormatException e) {
                error = "Please enter valid numeric marks.";
                break;
            }
        }
        if (error != null) {
            // On error, send message back to form
            req.setAttribute("message", error);
            req.getRequestDispatcher("GradeCalculator.jsp").forward(req, resp);
            return;
        }
        double average = (double) totalMarks / totalSubjects;
        String grade;
        if (average >= 90)      grade = "A+";
        else if (average >= 80) grade = "A";
        else if (average >= 70) grade = "B";
        else if (average >= 60) grade = "C";
        else if (average >= 50) grade = "D";
        else                    grade = "F";
        req.setAttribute("total", totalMarks);
        req.setAttribute("average", average);
        req.setAttribute("grade", grade);
        req.getRequestDispatcher("GradeResult.jsp").forward(req, resp);
    }
}