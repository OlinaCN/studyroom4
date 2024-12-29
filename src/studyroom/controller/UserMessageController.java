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

@WebServlet("/user/messages")
public class UserMessageController extends HttpServlet {
    private MessageService messageService;

    public UserMessageController() {
        this.messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 检查用户是否登录
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // 获取所有留言
            List<Message> messages = messageService.getAllMessages();
            request.setAttribute("messages", messages);
            request.getRequestDispatcher("/function.jsp").forward(request, response);
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    addMessage(request, response, user);
                    break;
                case "update":
                    updateMessage(request, response, user);
                    break;
                case "delete":
                    deleteMessage(request, response, user);
                    break;
                default:
                    response.sendRedirect(request.getContextPath() + "/user/messages");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addMessage(HttpServletRequest request, HttpServletResponse response, User user)
            throws SQLException, IOException {
        String content = request.getParameter("content");
        Message message = new Message();
        message.setContent(content);
        message.setUserId(user.getId());
        message.setUsername(user.getUsername());
        messageService.addMessage(message);
        response.sendRedirect(request.getContextPath() + "/user/messages");
    }

    private void updateMessage(HttpServletRequest request, HttpServletResponse response, User user)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        Message message = new Message();
        message.setId(id);
        message.setContent(content);
        message.setUserId(user.getId());
        messageService.updateMessage(message);
        response.sendRedirect(request.getContextPath() + "/user/messages");
    }

    private void deleteMessage(HttpServletRequest request, HttpServletResponse response, User user)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        messageService.deleteMessage(id, user.getId());
        response.sendRedirect(request.getContextPath() + "/user/messages");
    }
}