package pl.coderslab.servlets.exercisecontrol;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.models.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ExerciseShow", urlPatterns = "/exercise-show")
public class ExerciseShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Exercise> exercises = new ArrayList<>();
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                exercises.add(ExerciseDao.loadById(id));
            } catch (NumberFormatException ignored) {
            }
        } else {
            exercises = ExerciseDao.loadAll();
        }
        request.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/WEB-INF/exerciseview/exercise-list.jsp").forward(request, response);
    }
}
