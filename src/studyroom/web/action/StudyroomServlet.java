package studyroom.web.action;

import studyroom.service.StudyroomService;
import studyroom.service.Impl.StudyroomServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/studyroom/detail","/studyroom/add","/studyroom/delete","/studyroom/update","/studyroom/modify"})
public class StudyroomServlet extends HttpServlet {
    private StudyroomService studyroomService = new StudyroomServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ServletPath = request.getServletPath();
        if(ServletPath.equals("/studyroom/detail")){
            studyroomService.detail(request,response);
        } else if (ServletPath.equals("/studyroom/add")) {
            int flag = studyroomService.add(request, response);
            if (flag == 1) {
                response.sendRedirect(request.getContextPath()+"/studyroom/detail");
            } else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }
        } else if (ServletPath.equals("/studyroom/delete")) {
            int flag = studyroomService.delete(request, response);
            if (flag == 1) {
                response.sendRedirect(request.getContextPath()+"/studyroom/detail");
            }else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }
        } else if (ServletPath.equals("/studyroom/modify")) {
            int flag = studyroomService.modify(request, response);
            if (flag == 1) {
                response.sendRedirect(request.getContextPath()+"/studyroom/detail");
            } else {
                response.sendRedirect(request.getContextPath()+"/error.jsp");
            }
        } else if (ServletPath.equals("/studyroom/update")) {
            studyroomService.update(request, response);
        }
    }
}
