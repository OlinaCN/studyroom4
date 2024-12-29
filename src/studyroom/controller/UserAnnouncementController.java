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

@WebServlet("/user/announcements")
public class UserAnnouncementController extends HttpServlet {
    private AnnouncementService announcementService;

    public UserAnnouncementController() {
        this.announcementService = new AnnouncementService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 检查用户是否登录
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // 获取所有公告
            List<Announcement> announcements = announcementService.getAllAnnouncements();
            request.setAttribute("announcements", announcements);
            request.getRequestDispatcher("/userAnnouncements.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}