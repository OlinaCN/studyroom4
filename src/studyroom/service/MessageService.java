package studyroom.service;

import studyroom.dao.MessageDAO;
import studyroom.model.Message;

import java.sql.SQLException;
import java.util.List;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService() {
        this.messageDAO = new MessageDAO();
    }

    public List<Message> getAllMessages() throws SQLException {
        return messageDAO.getAllMessages();
    }

    public List<Message> getMessagesByUserId(int userId) throws SQLException {
        return messageDAO.getMessagesByUserId(userId);
    }

    public void addMessage(Message message) throws SQLException {
        messageDAO.addMessage(message);
    }

    public void updateMessage(Message message) throws SQLException {
        messageDAO.updateMessage(message);
    }

    public void deleteMessage(int id, int userId) throws SQLException {
        messageDAO.deleteMessage(id, userId);
    }

    public Message getMessageById(int id) throws SQLException {
        return messageDAO.getMessageById(id);
    }

    // 管理员专用的删除方法（不检查用户ID）
    public void adminDeleteMessage(int id) throws SQLException {
        String sql = "DELETE FROM message WHERE id = ?";
        try (java.sql.Connection conn = studyroom.util.DBUtil.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // 管理员专用的更新方法（不检查用户ID）
    public void adminUpdateMessage(Message message) throws SQLException {
        String sql = "UPDATE message SET content = ? WHERE id = ?";
        try (java.sql.Connection conn = studyroom.util.DBUtil.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, message.getContent());
            stmt.setInt(2, message.getId());
            stmt.executeUpdate();
        }
    }
}