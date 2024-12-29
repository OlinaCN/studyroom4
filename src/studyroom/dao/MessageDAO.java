package studyroom.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import studyroom.model.Message;
import studyroom.util.DBUtil;

public class MessageDAO {

    public List<Message> getAllMessages() throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message ORDER BY created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setContent(rs.getString("content"));
                message.setUserId(rs.getInt("user_id"));
                message.setUsername(rs.getString("username"));
                message.setCreatedAt(rs.getTimestamp("created_at"));
                message.setUpdatedAt(rs.getTimestamp("updated_at"));
                messages.add(message);
            }
        }
        return messages;
    }

    public List<Message> getMessagesByUserId(int userId) throws SQLException {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt("id"));
                message.setContent(rs.getString("content"));
                message.setUserId(rs.getInt("user_id"));
                message.setUsername(rs.getString("username"));
                message.setCreatedAt(rs.getTimestamp("created_at"));
                message.setUpdatedAt(rs.getTimestamp("updated_at"));
                messages.add(message);
            }
        }
        return messages;
    }

    public void addMessage(Message message) throws SQLException {
        String sql = "INSERT INTO message (content, user_id, username) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, message.getContent());
            stmt.setInt(2, message.getUserId());
            stmt.setString(3, message.getUsername());
            stmt.executeUpdate();
        }
    }

    public void updateMessage(Message message) throws SQLException {
        String sql = "UPDATE message SET content = ? WHERE id = ? AND user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, message.getContent());
            stmt.setInt(2, message.getId());
            stmt.setInt(3, message.getUserId());
            stmt.executeUpdate();
        }
    }

    public void deleteMessage(int id, int userId) throws SQLException {
        String sql = "DELETE FROM message WHERE id = ? AND user_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        }
    }

    public Message getMessageById(int id) throws SQLException {
        String sql = "SELECT * FROM message WHERE id = ?";
        Message message = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    message = new Message();
                    message.setId(rs.getInt("id"));
                    message.setContent(rs.getString("content"));
                    message.setUserId(rs.getInt("user_id"));
                    message.setUsername(rs.getString("username"));
                    message.setCreatedAt(rs.getTimestamp("created_at"));
                    message.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }
        return message;
    }
}