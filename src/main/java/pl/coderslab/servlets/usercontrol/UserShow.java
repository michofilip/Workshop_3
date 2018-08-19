package pl.coderslab.servlets.usercontrol;

import pl.coderslab.dao.UserDao;
import pl.coderslab.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserShow", urlPatterns = "/user-show")
public class UserShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        String idStr = request.getParameter("id");
        String groupIdStr = request.getParameter("groupId");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                users.add(UserDao.loadById(id));
            } catch (NumberFormatException ignored) {
            }
        } else if (groupIdStr != null) {
            try {
                int groupId = Integer.parseInt(groupIdStr);
                users = UserDao.loadAllByGroupId(groupId);
            } catch (NumberFormatException ignored) {
            }
        } else {
            users = UserDao.loadAll();
        }
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/userview/user-list.jsp").forward(request, response);
    }
}
