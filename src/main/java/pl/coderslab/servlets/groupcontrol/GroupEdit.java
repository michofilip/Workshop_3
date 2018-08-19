package pl.coderslab.servlets.groupcontrol;

import pl.coderslab.dao.GroupDao;
import pl.coderslab.models.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GroupEdit", urlPatterns = "/group-edit")
public class GroupEdit extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] strs = new String[]{
                request.getParameter("id"),
                request.getParameter("name"),
        };

        Group group = GroupDao.makeGroup(strs);
        GroupDao.save(group);

        response.sendRedirect("/group-show");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            Group group = GroupDao.loadById(id);
            request.setAttribute("group", group);
        } catch (NumberFormatException ignored) {
        }

        request.setAttribute("mode", "edit");

        getServletContext().getRequestDispatcher("/WEB-INF/groupview/group-form.jsp").forward(request, response);
    }
}
