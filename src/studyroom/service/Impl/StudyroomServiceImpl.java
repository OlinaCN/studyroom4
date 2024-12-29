package studyroom.service.Impl;

import studyroom.bean.Studyrooms;
import studyroom.dao.StudyroomsDao;
import studyroom.dao.impl.StudyroomsDaoImpl;
import studyroom.service.StudyroomService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class StudyroomServiceImpl implements StudyroomService {
    private StudyroomsDao studyroomsDao = new StudyroomsDaoImpl();
    @Override
    public int add(HttpServletRequest request, HttpServletResponse response) {
        Studyrooms studyrooms = new Studyrooms(false);
        return studyroomsDao.addStudyrooms(studyrooms);
    }

    @Override
    public int delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        return studyroomsDao.deleteById(id);
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = studyroomsDao.checkById(id);
        Studyrooms studyrooms = new Studyrooms(id,status);
        request.setAttribute("studyrooms", studyrooms);
        request.getRequestDispatcher("/editStudyroom.jsp").forward(request,response);
    }

    @Override
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Studyrooms> list = studyroomsDao.getAllStudyrooms();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/b_detail.jsp").forward(request,response);
    }

    @Override
    public int modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Objects.equals(request.getParameter("status"), "available");
        Studyrooms studyrooms = new Studyrooms(id,status);
        return studyroomsDao.updateStudyrooms(studyrooms);
    }
}
