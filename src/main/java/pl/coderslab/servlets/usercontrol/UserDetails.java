package pl.coderslab.servlets.usercontrol;

import pl.coderslab.dao.SolutionDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.Solution;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserDetails", urlPatterns = "/user-details")
public class UserDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            User user = UserDao.loadById(id);
            ArrayList<Solution> solutions = SolutionDao.loadAllByUserId(id);
            request.setAttribute("user", user);
            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/WEB-INF/userview/user-details.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("/WEB-INF/userview/user-list.jsp");
        }
    }
}
