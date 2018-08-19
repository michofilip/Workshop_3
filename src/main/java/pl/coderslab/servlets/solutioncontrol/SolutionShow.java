package pl.coderslab.servlets.solutioncontrol;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.models.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SolutionShow", urlPatterns = "/solution-show")
public class SolutionShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Solution> solutions = new ArrayList<>();
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                solutions.add(SolutionDao.loadById(id));
            } catch (NumberFormatException ignored) {
            }
        } else {
            solutions = SolutionDao.loadAll();
        }
        request.setAttribute("solutions", solutions);
        getServletContext().getRequestDispatcher("/WEB-INF/solutionview/solution-list.jsp").forward(request, response);
    }
}
