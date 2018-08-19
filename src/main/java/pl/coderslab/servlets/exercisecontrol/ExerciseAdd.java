package pl.coderslab.servlets.exercisecontrol;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ExerciseAdd", urlPatterns = "/exercise-add")
public class ExerciseAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] strs = new String[]{
                request.getParameter("id"),
                request.getParameter("title"),
                request.getParameter("description"),
        };

        Exercise exercise = ExerciseDao.makeExercise(strs);
        ExerciseDao.save(exercise);

        response.sendRedirect("/exercise-show");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mode", "add");
        getServletContext().getRequestDispatcher("/WEB-INF/exerciseview/exercise-form.jsp").forward(request, response);
    }
}
