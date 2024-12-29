package studyroom.dao.impl;

import studyroom.bean.Studyrooms;
import studyroom.bean.Users;
import studyroom.bean.reservations;
import studyroom.dao.ReservationsDao;
import studyroom.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservationsDaoImpl implements ReservationsDao {
    @Override
    public int addReservation(Users users, Studyrooms studyrooms) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into reservations(user_name,studyroom_id) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,users.getName());
            ps.setLong(2,studyrooms.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }

    @Override
    public int deleteReservation(Users users, Studyrooms studyrooms) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from reservations where user_name=? and studyroom_id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,users.getName());
            ps.setLong(2,studyrooms.getId());
            count = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, null);
        }
        return count;
    }

    @Override
    public String selectByStudyroomId(Long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from reservations where studyroom_id=?";
            ps = conn.prepareStatement(sql);
            ps.setLong(1,id);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("user_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    @Override
    public long selectByUserName(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        long result = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from reservations where user_name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getLong("studyroom_id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return result;
    }

    @Override
    public List<reservations> selectAllReservations() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<reservations> list = new ArrayList<reservations>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from reservations";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String user_name = rs.getString("user_name");
                long studyroom_id = rs.getLong("studyroom_id");
                long id = rs.getLong("id");
                list.add(new reservations(id,user_name,studyroom_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }
}
