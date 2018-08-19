package pl.coderslab.servlets.groupcontrol;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GroupShow", urlPatterns = "/group-show")
public class GroupShow extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Group> groups = new ArrayList<>();
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                groups.add(GroupDao.loadById(id));
            } catch (NumberFormatException ignored) {
            }
        } else {
            groups = GroupDao.loadAll();
        }
        request.setAttribute("groups", groups);
        getServletContext().getRequestDispatcher("/WEB-INF/groupview/group-list.jsp").forward(request, response);
    }
}
