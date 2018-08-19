package pl.coderslab.servlets.solutioncontrol;

import pl.coderslab.dao.ExerciseDao;
import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.Exercise;
import pl.coderslab.models.Solution;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SolutionEdit", urlPatterns = "/solution-edit")
public class SolutionEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String createdDate = request.getParameter("createdDate");
        String createdTime = request.getParameter("createdTime");
        String updatedDate = request.getParameter("updatedDate");
        String updatedTime = request.getParameter("updatedTime");
        String created = createdDate + " " + createdTime + ":00";

        String updated = null;
        if (!updatedDate.isEmpty() && !updatedTime.isEmpty()) {
            updated = updatedDate + " " + updatedTime + ":00";
        }

        String[] strs = new String[]{
                request.getParameter("id"),
                created,
                updated,
                request.getParameter("description"),
                request.getParameter("exercise"),
                request.getParameter("user")
        };

        Solution solution = SolutionDao.makeSolution(strs);
        SolutionDao.save(solution);

        response.sendRedirect("/solution-show");

//        String[] strs = new String[]{
//                request.getParameter("id"),
//                request.getParameter("created"),
//                request.getParameter("updated"),
//                request.getParameter("description"),
//                request.getParameter("exercise"),
//                request.getParameter("user")
//        };
//
//        Solution solution = SolutionDao.makeSolution(strs);
//        SolutionDao.save(solution);
//
//        response.sendRedirect("/solution-show");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Solution solution = SolutionDao.loadById(id);
            request.setAttribute("solution", solution);
        } catch (NumberFormatException ignored) {
        }

        ArrayList<Exercise> exercises = ExerciseDao.loadAll();
        ArrayList<User> users = UserDao.loadAll();
        request.setAttribute("mode", "edit");
        request.setAttribute("exercises", exercises);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/solutionview/solution-form.jsp").forward(request, response);
    }
}
