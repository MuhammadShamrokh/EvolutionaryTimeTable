package Servlets;

import Utils.SessionUtils;
import Constants.Constants;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//url: localHost:8080/TimeTable/index
public class IndexServlet extends HttpServlet {

    private void processRequest(HttpServletRequest i_request, HttpServletResponse i_Response) throws IOException, ServletException{
        i_Response.setContentType("text/plain;charset=UTF-8");
        String userID= SessionUtils.getUserID(i_request);
        if(userID==null){
            i_Response.getOutputStream().println(Constants.LOGIN_PAGE_PATH);
        }
        else{
            i_Response.getOutputStream().println(Constants.HOME_PAGE_PATH);
        }
        i_Response.setStatus(200);
    }





    @Override
    protected void doPost(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
    @Override
    protected void doGet(HttpServletRequest i_Request, HttpServletResponse i_Response)
            throws ServletException, IOException {
        processRequest(i_Request, i_Response);
    }
}
