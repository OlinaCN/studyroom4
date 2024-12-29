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

@WebServlet("/edit-message")
public class EditMessageController extends HttpServlet {
    private MessageService messageService;

    public EditMessageController() {
        this.messageService = new MessageService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try {
            // 检查用户是否登录
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            // 获取留言ID
            int id = Integer.parseInt(request.getParameter("id"));
            Message message = messageService.getMessageById(id);

            // 检查留言是否存在
            if (message == null) {
                response.sendRedirect(request.getContextPath() + "/user/messages");
                return;
            }

            // 检查是否是管理员或留言作者
            boolean isAdmin = "admin".equals(user.getRole());
            if (!isAdmin && message.getUserId() != user.getId()) {
                response.sendRedirect(request.getContextPath() + "/user/messages");
                return;
            }

            // 将留言信息传递给编辑页面
            request.setAttribute("message", message);
            request.setAttribute("isAdmin", isAdmin);

            // 根据用户角色转发到不同的编辑页面
            String editPage = isAdmin ? "/admin/editMessage.jsp" : "/editMessage.jsp";
            request.getRequestDispatcher(editPage).forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/user/messages");
        }
    }
}