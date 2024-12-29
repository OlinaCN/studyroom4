package studyroom.dao.impl;

import studyroom.bean.Studyrooms;
import studyroom.dao.StudyroomsDao;
import studyroom.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudyroomsDaoImpl implements StudyroomsDao {
    @Override
    public int addStudyrooms(Studyrooms studyrooms) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into studyrooms(status) values(?)";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, studyrooms.isStatus());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public int deleteById(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from studyrooms where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public List<Studyrooms> getAllStudyrooms() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Studyrooms> list = new ArrayList<Studyrooms>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from studyrooms";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                boolean status = rs.getBoolean("status");
                Studyrooms studyrooms = new Studyrooms(id, status);
                list.add(studyrooms);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return list;
    }

    @Override
    public int updateStudyrooms(Studyrooms studyrooms) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update studyrooms set status = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setBoolean(1, studyrooms.isStatus());
            ps.setLong(2, studyrooms.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn,ps,null);
        }
        return count;
    }

    @Override
    public boolean checkById(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean status = false;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from studyrooms where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                status = rs.getBoolean("status");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        return status;
    }
}
