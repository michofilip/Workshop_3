package pl.coderslab.servlets.usercontrol;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.dao.UserDao;
import pl.coderslab.models.Group;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UserAdd", urlPatterns = "/user-add")
public class UserAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] strs = new String[]{
                request.getParameter("id"),
                request.getParameter("username"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("group"),
        };

        User user = UserDao.makeUser(strs);
        UserDao.save(user);

        response.sendRedirect("/user-show");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Group> groups = GroupDao.loadAll();
        request.setAttribute("mode", "add");
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/userview/user-form.jsp").forward(request, response);
    }
}
