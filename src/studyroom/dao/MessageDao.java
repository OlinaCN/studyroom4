package studyroom.dao;

import studyroom.bean.Message;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MessageDao 负责对留言进行增删改查操作。
 */
public class MessageDao {

    // 根据项目实际情况修改数据库连接方式
    private static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        // 请根据实际数据库信息进行修改
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studyroom", "root", "password");
    }

    /**
     * 添加留言
     */
    public static boolean addMessage(Message msg) {
        String sql = "INSERT INTO message(user_id, content, create_time) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, msg.getUserId());
            pstmt.setString(2, msg.getContent());
            pstmt.setTimestamp(3, new Timestamp(msg.getCreateTime().getTime()));
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更新留言（仅限用户本人或管理员）
     */
    public static boolean updateMessage(Message msg) {
        String sql = "UPDATE message SET content=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, msg.getContent());
            pstmt.setInt(2, msg.getId());
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除留言（仅限用户本人或管理员）
     */
    public static boolean deleteMessage(int messageId) {
        String sql = "DELETE FROM message WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, messageId);
            int rows = pstmt.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据留言ID获取留言
     */
    public static Message getMessageById(int msgId) {
        String sql = "SELECT * FROM message WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, msgId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setUserId(rs.getInt("user_id"));
                m.setContent(rs.getString("content"));
                m.setCreateTime(rs.getTimestamp("create_time"));
                return m;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有留言
     */
    public static List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM message ORDER BY create_time DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Message m = new Message();
                m.setId(rs.getInt("id"));
                m.setUserId(rs.getInt("user_id"));
                m.setContent(rs.getString("content"));
                m.setCreateTime(rs.getTimestamp("create_time"));
                list.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取某个用户的所有留言
     */
    public static List<Message> getMessagesByUserId(int userId) {
        List<Message> list = new ArrayList<>();
        String sql = "SELECT * FROM message WHERE user_id=? ORDER BY create_time DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Message m = new Message();
                    m.setId(rs.getInt("id"));
                    m.setUserId(rs.getInt("user_id"));
                    m.setContent(rs.getString("content"));
                    m.setCreateTime(rs.getTimestamp("create_time"));
                    list.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}