package studyroom.controller;

import studyroom.model.Message;
import studyroom.model.User;
import studyroom.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/messages")
public class AdminMessageController extends HttpServlet {
    private MessageService messageService;

    public AdminMessageController() {
        this.messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 检查用户是否为管理员
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null || !"admin".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // 获取所有留言
            List<Message> messages = messageService.getAllMessages();
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 检查用户是否为管理员
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !"admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "update":
                    updateMessage(request, response);
                    break;
                case "delete":
                    deleteMessage(request, response);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/admin/messages");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateMessage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        Message message = new Message();
        message.setId(id);
        message.setContent(content);
        messageService.adminUpdateMessage(message);
        response.sendRedirect(request.getContextPath() + "/admin/messages");
    }

    private void deleteMessage(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        messageService.adminDeleteMessage(id);
        response.sendRedirect(request.getContextPath() + "/admin/messages");
    }
}