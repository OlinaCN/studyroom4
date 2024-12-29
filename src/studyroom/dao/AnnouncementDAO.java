package studyroom.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import studyroom.model.Announcement;
import studyroom.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import studyroom.model.Announcement;
import studyroom.util.DBUtil;

public class AnnouncementDAO {

    public List<Announcement> getAllAnnouncements() throws SQLException {
        List<Announcement> announcements = new ArrayList<>();
        String sql = "SELECT * FROM announcement ORDER BY created_at DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setId(rs.getInt("id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setCreatedAt(rs.getTimestamp("created_at"));
                announcement.setUpdatedAt(rs.getTimestamp("updated_at"));
                announcements.add(announcement);
            }
        }

        return announcements;
    }

    public void addAnnouncement(Announcement announcement) throws SQLException {
        String sql = "INSERT INTO announcement (title, content) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, announcement.getTitle());
            stmt.setString(2, announcement.getContent());
            stmt.executeUpdate();
        }
    }

    public void updateAnnouncement(Announcement announcement) throws SQLException {
        String sql = "UPDATE announcement SET title = ?, content = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, announcement.getTitle());
            stmt.setString(2, announcement.getContent());
            stmt.setInt(3, announcement.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteAnnouncement(int id) throws SQLException {
        String sql = "DELETE FROM announcement WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    public Announcement getAnnouncementById(int id) throws SQLException {
        String sql = "SELECT * FROM announcement WHERE id = ?";
        Announcement announcement = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    announcement = new Announcement();
                    announcement.setId(rs.getInt("id"));
                    announcement.setTitle(rs.getString("title"));
                    announcement.setContent(rs.getString("content"));
                    announcement.setCreatedAt(rs.getTimestamp("created_at"));
                    announcement.setUpdatedAt(rs.getTimestamp("updated_at"));
                }
            }
        }

        return announcement;
    }
}