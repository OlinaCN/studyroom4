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
import studyroom.service.FunctionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class FunctionServiceImpl implements FunctionService {
    private UsersDao usersDao = new UsersDaoImpl();
    private StudyroomsDao studyroomsDao = new StudyroomsDaoImpl();
    private ReservationsDao reservationsDao = new ReservationsDaoImpl();
    @Override
    public void select(HttpServletRequest request, HttpServletResponse response) {
        List<Studyrooms> list = new ArrayList<>();
        list = studyroomsDao.getAllStudyrooms();
        request.setAttribute("studyrooms", list);
    }

    @Override
    public boolean deal(HttpServletRequest request, HttpServletResponse response) {
        long study_id =  Long.parseLong(request.getParameter("study_id"));
        boolean status = studyroomsDao.checkById(study_id);
        //如果浴室不可用
        if(!status){
            return false;
        }
        status = false;
        Studyrooms studyrooms = new Studyrooms(study_id,status);
        Users user = new Users();
        HttpSession session = request.getSession();
        user = (Users) session.getAttribute("user");
        int num = user.getNum();
        //余额次数够用
        if(num>0){
            num--;
            user.setNum(num);
            session.setAttribute("user", user);
            //更新数据库
            usersDao.updateUsers(user);
            //更新浴室状态
            studyroomsDao.updateStudyrooms(studyrooms);
            //更新订单状态
            reservationsDao.addReservation(user,studyrooms);
            return true;
        }
        return false;
    }

    @Override
    public void order(HttpServletRequest request, HttpServletResponse response) {
        List <reservations> list = new ArrayList<>();
        list = reservationsDao.selectAllReservations();
        request.setAttribute("list", list);
    }

    @Override
    public void end(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        Long studyroom_id = Long.parseLong(request.getParameter("studyroom_id"));
        Users user = usersDao.selectByUsername(username);
        boolean status = studyroomsDao.checkById(studyroom_id);
        Studyrooms studyrooms = new Studyrooms(studyroom_id,status);
        //修改浴室状态
        status = !status;
        studyrooms.setStatus(status);
        studyroomsDao.updateStudyrooms(studyrooms);
        //删除订单
        reservationsDao.deleteReservation(user,studyrooms);
    }
}
