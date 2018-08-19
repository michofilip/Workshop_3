package pl.coderslab.servlets.exercisecontrol;

import pl.coderslab.dao.ExerciseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExerciseDel", urlPatterns = "/exercise-del")
public class ExerciseDel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            ExerciseDao.delete(id);
        } catch (NumberFormatException ignored) {
        }
        response.sendRedirect("/exercise-show");
    }
}
