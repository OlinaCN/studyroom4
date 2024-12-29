package studyroom.controller;

import studyroom.model.Announcement;
import studyroom.service.AnnouncementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditAnnouncementController extends HttpServlet {
    private AnnouncementService announcementService;

    public EditAnnouncementController() {
        this.announcementService = new AnnouncementService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Announcement announcement = announcementService.getAnnouncementById(id);
            request.setAttribute("announcement", announcement);
            request.getRequestDispatcher("/editAnnouncement.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}