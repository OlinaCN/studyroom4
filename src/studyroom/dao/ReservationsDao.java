package studyroom.dao;

import studyroom.bean.Studyrooms;
import studyroom.bean.Users;
import studyroom.bean.reservations;

import java.util.List;

public interface ReservationsDao {
    int addReservation(Users users, Studyrooms studyrooms);
    int deleteReservation(Users users, Studyrooms studyrooms);
    String selectByStudyroomId(Long id);
    long selectByUserName(String username);
    List<reservations> selectAllReservations();
}
