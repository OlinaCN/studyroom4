package studyroom.controller;

import studyroom.model.Announcement;
import studyroom.service.AnnouncementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/announcements")
public class AnnouncementController extends HttpServlet {
    private AnnouncementService announcementService;

    public AnnouncementController() {
        this.announcementService = new AnnouncementService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            List<Announcement> announcements = announcementService.getAllAnnouncements();
            request.setAttribute("announcements", announcements);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");

        try {
            if ("add".equals(action)) {
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Announcement announcement = new Announcement();
                announcement.setTitle(title);
                announcement.setContent(content);
                announcementService.addAnnouncement(announcement);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String content = request.getParameter("content");
                Announcement announcement = new Announcement();
                announcement.setId(id);
                announcement.setTitle(title);
                announcement.setContent(content);
                announcementService.updateAnnouncement(announcement);
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                announcementService.deleteAnnouncement(id);
            }
            response.sendRedirect("announcements");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}