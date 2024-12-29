package studyroom.service.Impl;

import studyroom.bean.Studyrooms;
import studyroom.bean.Users;
import studyroom.bean.reservations;
import studyroom.dao.StudyroomsDao;
import studyroom.dao.ReservationsDao;
import studyroom.dao.UsersDao;
import studyroom.dao.impl.StudyroomsDaoImpl;
import studyroom.dao.impl.ReservationsDaoImpl;
import studyroom.dao.impl.UsersDaoImpl;
import studyroom.service.StudyroomService;
import studyroom.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private UsersDao usersDao = new UsersDaoImpl();
    private StudyroomsDao studyroomsDao = new StudyroomsDaoImpl();
    private ReservationsDao reservationsDao = new ReservationsDaoImpl();
    @Override
    public int add(HttpServletRequest request, HttpServletResponse response) {
        return 0;
    }

    @Override
    public int delete(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Users user = usersDao.selectByUsername(name);
        long id = reservationsDao.selectByUserName(name);
        boolean status = studyroomsDao.checkById(id);
        Studyrooms studyroom = new Studyrooms(id,status);
        return reservationsDao.deleteReservation(user,studyroom);
    }

    @Override
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<reservations> list = new ArrayList<>();
        list = reservationsDao.selectAllReservations();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/o_detail.jsp").forward(request,response);
    }

}
