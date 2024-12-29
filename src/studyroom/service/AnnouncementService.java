package studyroom.service;

import studyroom.dao.AnnouncementDAO;
import studyroom.model.Announcement;

import java.sql.SQLException;
import java.util.List;

public class AnnouncementService {
    private AnnouncementDAO announcementDAO;

    public AnnouncementService() {
        this.announcementDAO = new AnnouncementDAO();
    }

    public List<Announcement> getAllAnnouncements() throws SQLException {
        return announcementDAO.getAllAnnouncements();
    }

    public void addAnnouncement(Announcement announcement) throws SQLException {
        announcementDAO.addAnnouncement(announcement);
    }

    public void updateAnnouncement(Announcement announcement) throws SQLException {
        announcementDAO.updateAnnouncement(announcement);
    }

    public void deleteAnnouncement(int id) throws SQLException {
        announcementDAO.deleteAnnouncement(id);
    }
    public Announcement getAnnouncementById(int id) throws SQLException {
        return announcementDAO.getAnnouncementById(id);
    }
}