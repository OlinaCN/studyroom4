package studyroom.servlet;

import studyroom.bean.Message;
import studyroom.dao.MessageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * MessageServlet 处理添加、修改、删除以及查看留言的请求
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 统一让 GET 请求和 POST 请求的处理方式相同
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        // 从 session 中获取用户信息（假设保存了 userId 和 isAdmin）
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        if (userId == null) {
            // 未登录，跳转到登录界面
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        if ("add".equals(action)) {
            // 新增留言
            String content = request.getParameter("content");
            Message msg = new Message();
            msg.setUserId(userId);
            msg.setContent(content);
            msg.setCreateTime(new Date());

            MessageDao.addMessage(msg);
            // 跳转回用户功能界面或其他页面
            response.sendRedirect(request.getContextPath() + "/function.jsp");

        } else if ("update".equals(action)) {
            // 更新留言
            int msgId = Integer.parseInt(request.getParameter("id"));
            Message oldMsg = MessageDao.getMessageById(msgId);
            if (oldMsg != null) {
                // 检查是否为本人或管理员
                if (oldMsg.getUserId() == userId || (isAdmin != null && isAdmin)) {
                    String newContent = request.getParameter("content");
                    oldMsg.setContent(newContent);
                    MessageDao.updateMessage(oldMsg);
                }
            }
            // 重定向
            if (isAdmin != null && isAdmin) {
                response.sendRedirect(request.getContextPath() + "/list.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/function.jsp");
            }

        } else if ("delete".equals(action)) {
            // 删除留言
            int msgId = Integer.parseInt(request.getParameter("id"));
            Message oldMsg = MessageDao.getMessageById(msgId);
            if (oldMsg != null) {
                // 检查是否为本人或管理员
                if (oldMsg.getUserId() == userId || (isAdmin != null && isAdmin)) {
                    MessageDao.deleteMessage(msgId);
                }
            }
            // 重定向
            if (isAdmin != null && isAdmin) {
                response.sendRedirect(request.getContextPath() + "/list.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/function.jsp");
            }

        } else {
            // 默认操作：查询并展示留言
            if (isAdmin != null && isAdmin) {
                // 管理员查看所有留言
                List<Message> allMessages = MessageDao.getAllMessages();
                request.setAttribute("messageList", allMessages);
                request.getRequestDispatcher("/list.jsp").forward(request, response);
            } else {
                // 普通用户查看自己的留言
                List<Message> userMessages = MessageDao.getMessagesByUserId(userId);
                request.setAttribute("messageList", userMessages);
                request.getRequestDispatcher("/function.jsp").forward(request, response);
            }
        }
    }
}